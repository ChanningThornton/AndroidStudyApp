package com.cse5236.headsUpStudy

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepage)

        Log.d("MainActivity", "onCreate called")

        val loginButton = findViewById<Button>(R.id.login_button)
        loginButton.setOnClickListener (this)

        val howToButton = findViewById<Button>(R.id.how_button)
        howToButton.setOnClickListener(this)

        val categoriesButton = findViewById<Button>(R.id.categories_button)
        categoriesButton.setOnClickListener(this)

        val newGameButton = findViewById<Button>(R.id.newGame_button)
        newGameButton.setOnClickListener(this)

        FirebaseAuth.getInstance().addAuthStateListener { firebaseAuth ->
            if(firebaseAuth.currentUser != null){
                loginButton.text = "Logout"
            } else {
                loginButton.text = "Login"
            }
        }

    }

    override fun onClick(v: View?){
        when (v?.id) {
            R.id.login_button -> {
                val currentUser = FirebaseAuth.getInstance().currentUser
                if(currentUser != null){
                    FirebaseAuth.getInstance().signOut()
                } else {
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                }
            }
            R.id.how_button -> {
                val dialog = HowToPlayFragment()

                dialog.show(supportFragmentManager, "customDialog")
            }
            R.id.categories_button -> {
                val intent = Intent(this, CategoriesActivity::class.java)
                startActivity(intent)
            }
            R.id.newGame_button -> {
                val intent = Intent(this, NewGameActivity::class.java)
                startActivity(intent)
            }
            else -> Log.e("LoginActivity", "Error: Invalid button press")
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

