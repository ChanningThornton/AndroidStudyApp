package com.cse5236.headsUpStudy.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.cse5236.headsUpStudy.R

class NewGameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.newgame)

        Log.d("NewGameActivity", "onCreate called")

        val backButton = findViewById<Button>(R.id.back)
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        /*
        val startButton = findViewById<Button>(R.id.start_button)
        startButton.setOnClickListener{
            val intent = Intent(this, StartGameActivity::class.java)
            startActivity(intent)
         */
    }

    override fun onStart() {
        super.onStart()
        Log.d("NewGameActivity", "onStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.d("NewGameActivity", "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.d("NewGameActivity", "onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.d("NewGameActivity", "onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("NewGameActivity", "onDestroy called")
    }

}