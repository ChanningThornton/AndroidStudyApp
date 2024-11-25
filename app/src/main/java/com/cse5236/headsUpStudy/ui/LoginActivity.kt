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
import com.cse5236.headsUpStudy.ModelView.LoginViewModel
import com.cse5236.headsUpStudy.R

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private val loginViewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        Log.d("LoginActivity", "onCreate called")

        val backButton = findViewById<Button>(R.id.back_button)
        backButton.setOnClickListener(this);
        val signUpButton = findViewById<Button>(R.id.signup_button);
        signUpButton.setOnClickListener(this);
        val loginButton = findViewById<Button>(R.id.login_button);
        loginButton.setOnClickListener(this);
    }

    override fun onClick(v: View?){
        when (v?.id) {
            R.id.signup_button -> {
                val intent = Intent(this, SignUpActivity::class.java)
                startActivity(intent)
            }
            R.id.back_button -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            R.id.login_button -> {
                val email = findViewById<EditText>(R.id.editTextUsername)
                val password = findViewById<EditText>(R.id.editTextTextPassword)
                Log.d("login", email.text.toString());
                loginViewModel.login(email.text.toString(), password.text.toString())
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            else -> Log.e("LoginActivity", "Error: Invalid button press")
        }
    }
}