package com.cse5236.headsUpStudy.ui

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.cse5236.headsUpStudy.ModelView.GameViewModel
import com.cse5236.headsUpStudy.R
import com.cse5236.headsUpStudy.game.Game
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth

class GameActivity : AppCompatActivity(), View.OnClickListener, SensorEventListener {
    private val gameViewModel: GameViewModel by viewModels()
    private lateinit var sensorManager: SensorManager
    private lateinit var game: Game
    private var accelerometer: Sensor? = null
    private lateinit var wordText: TextView
    private lateinit var timerText: TextView
    private lateinit var backGround: View
    private var resetTilt: Boolean = true
    private lateinit var gameTimer: CountDownTimer
    private var streak: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val categoryId = intent.getStringExtra("CATEGORY_ID")
        val timeLimit = intent.getStringExtra("TIME_LIMIT")

        wordText = findViewById<TextView>(R.id.game_word)
        timerText = findViewById<TextView>(R.id.game_time)
        backGround = findViewById(R.id.main)

        gameViewModel.streak.observe(this) { streak ->
            gameViewModel.words.observe(this) { words ->
                if (words.isNotEmpty()) {
                    game = Game(categoryId ?: "", words, streak)
                    game.startGame()
                    displayWord(true)
                }
            }
            gameViewModel.loadCategory(categoryId)
        }
        gameViewModel.getStreak(FirebaseAuth.getInstance().currentUser?.uid ?: "")


        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)


        val convertedTimeLimit = convertTime(timeLimit ?: "1:00")
        timer(convertedTimeLimit)

        val backButton = findViewById<Button>(R.id.game_back_button)
        backButton.setOnClickListener(this)

    }
    //funstion to pull words and their status
    private fun getWordsStatusAsString(): ArrayList<String> {
        val wordsStatus = game.getWordsStatus()
        return ArrayList(wordsStatus.map { "${it.first}:${it.second}" })
    }
    private fun timer(timeLimit: Long) {
         gameTimer = object : CountDownTimer(timeLimit, 1000) {
             override fun onTick(millisUntilFinished: Long) {
                 val min = millisUntilFinished / 1000 / 60
                 val sec = millisUntilFinished / 1000 % 60
                 timerText.text = String.format("%d:%02d", min, sec)
             }

             override fun onFinish() {
                 val score = calculateScore()
                 val intent = Intent(this@GameActivity, ScoreActivity::class.java)
                 intent.putExtra("SCORE", score)
                 intent.putExtra("STREAK", streak)
                 intent.putStringArrayListExtra("WORDS_STATUS", getWordsStatusAsString())
                 startActivity(intent)
                 finish()


             }

         }
        gameTimer.start()
    }
    private fun calculateScore(): Int {

        return game.getWordsStatus().count { !it.second }
    }
    private fun convertTime(timeLimit: String): Long {
        val minSec = timeLimit.split(":")
        return (minSec[0].toLong() * 60 + minSec[1].toLong()) * 1000
    }

    private fun displayWord(isSkipped: Boolean){
        if(this::game.isInitialized){
            val nextCard = game.nextCard(isSkipped)
            if(nextCard == null){
                streak = game.getStreak()
                gameViewModel.updateStreak(FirebaseAuth.getInstance().currentUser?.uid ?: "", streak)
                val score = calculateScore()
                val intent = Intent(this@GameActivity, ScoreActivity::class.java)
                intent.putExtra("SCORE", "$score/${game.getNumCards()}")
                intent.putExtra("STREAK", streak)
                intent.putStringArrayListExtra("WORDS_STATUS", getWordsStatusAsString())
                startActivity(intent)
                finish()
            } else {
                wordText.text = nextCard.word
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id){
            R.id.game_back_button -> {
                val intent = Intent(this, NewGameActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    override fun onSensorChanged(event: SensorEvent) {
        Log.d("zAxis", "" + event.values[2])
        val zAxis = event.values[2]

        if(resetTilt){
            if (zAxis > 8) {
                backGround.setBackgroundColor(Color.GREEN)
                Handler(Looper.getMainLooper()).postDelayed({
                    backGround.setBackgroundColor(Color.WHITE)
                }, 250)
                resetTilt = false
                displayWord(false)
            } else if (zAxis < -8) {
                backGround.setBackgroundColor(Color.RED)
                Handler(Looper.getMainLooper()).postDelayed({
                    backGround.setBackgroundColor(Color.WHITE)
                }, 250)
                resetTilt = false
                displayWord(true)
            }
        } else {
            if(zAxis > -3 && zAxis < 3) resetTilt = true
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onResume() {
        super.onResume()
        Log.d("GameActivity", "onResume called")
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

}