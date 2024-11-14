package com.cse5236.headsUpStudy.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.cse5236.headsUpStudy.R

class ScoreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        val score = intent.getIntExtra("SCORE", 0)
        val streak = intent.getIntExtra("STREAK", 0)
        val wordsStatusStrings = intent.getStringArrayListExtra("WORDS_STATUS")

        val scoreText = findViewById<TextView>(R.id.score_value)
        scoreText.text = score.toString()

        val streakText = findViewById<TextView>(R.id.streak_value)
        streakText.text = streak.toString()


        val wordsStatusText = findViewById<TextView>(R.id.words_status)
        wordsStatusStrings?.let {
            val wordsStatus = it.map { str ->
                val parts = str.split(":")
                Pair(parts[0], parts[1].toBoolean())
            }
            val statusText = wordsStatus.joinToString("\n") { wordStatus ->
                "${wordStatus.first}: ${if (wordStatus.second) "Skipped" else "Guessed"}"
            }
            wordsStatusText.text = statusText

            val backButton = findViewById<Button>(R.id.back_button)
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}}