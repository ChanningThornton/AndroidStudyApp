package com.cse5236.headsUpStudy.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cse5236.headsUpStudy.ModelView.FirebaseRepository
import com.cse5236.headsUpStudy.R
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val repository = FirebaseRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepage)

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
                loginButton.text = getString(R.string.logout_button);
            } else {
                loginButton.text = getString(R.string.login_button);
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
                val currentUser = FirebaseAuth.getInstance().currentUser
                if(currentUser != null){
                    val intent = Intent(this, NewGameActivity::class.java)
                    startActivity(intent)
                } else {
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                }
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

