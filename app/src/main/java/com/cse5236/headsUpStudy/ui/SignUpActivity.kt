package com.cse5236.headsUpStudy.ui

import android.content.Intent
import android.os.Bundle
import android.os.Debug
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.cse5236.headsUpStudy.ModelView.SignUpViewModel
import com.cse5236.headsUpStudy.R

class SignUpActivity : AppCompatActivity(), View.OnClickListener {
    private val signUpViewModel: SignUpViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)

        val signUpButton = findViewById<Button>(R.id.signup_button)
        signUpButton.setOnClickListener(this)

        val backButton = findViewById<Button>(R.id.back_button)
        backButton.setOnClickListener(this);
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.back_button -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
            R.id.signup_button -> {
                val email = findViewById<EditText>(R.id.editTextUsername)
                val password = findViewById<EditText>(R.id.editTextPassword)
                signUpViewModel.signUp(email.text.toString(), password.text.toString(), this)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            else -> Log.e("LoginActivity", "Error: Invalid button press")
        }
    }

}