package com.cse5236.headsUpStudy

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class CategoriesActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        Log.d("CategoriesActivity", "onCreate called")

        val backButton = findViewById<Button>(R.id.back_button)
        backButton.setOnClickListener(this)
        val newCatButton = findViewById<Button>(R.id.newCat_button)
        newCatButton.setOnClickListener(this)
    }

    override fun onClick(v: View?){
        when (v?.id) {
            R.id.back_button -> {
                finish()
            }
            R.id.newCat_button -> {
                val currentUser = FirebaseAuth.getInstance().currentUser
                if(currentUser != null) {
                    val intent = Intent(this, NewCategoryActivity::class.java)
                    startActivity(intent)
                } else{
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                }
            }
            else -> Log.e("LoginActivity", "Error: Invalid button press")
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