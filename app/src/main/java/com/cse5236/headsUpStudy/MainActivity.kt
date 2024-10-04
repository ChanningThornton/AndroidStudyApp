package com.cse5236.headsUpStudy

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepage)

        Log.d("MainActivity", "onCreate called")

        val loginButton = findViewById<Button>(R.id.login_button)
        loginButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        val howToButton = findViewById<Button>(R.id.how_button)
        howToButton.setOnClickListener{
            val dialog = HowToPlayFragment()

            dialog.show(supportFragmentManager, "customDialog")
        }
    }



    override fun onStart() {
        super.onStart()
        Log.d("MainActivity", "onStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity", "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivity", "onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MainActivity", "onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity", "onDestroy called")
    }

}

