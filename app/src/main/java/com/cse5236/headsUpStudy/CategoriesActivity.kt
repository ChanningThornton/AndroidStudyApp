package com.cse5236.headsUpStudy

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class CategoriesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        Log.d("CategoriesActivity", "onCreate called")

        val backButton = findViewById<Button>(R.id.back_button)
        backButton.setOnClickListener {
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("CategoriesActivity", "onStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.d("CategoriesActivity", "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.d("CategoriesActivity", "onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.d("CategoriesActivity", "onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("CategoriesActivity", "onDestroy called")
    }
}