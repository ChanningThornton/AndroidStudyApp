package com.cse5236.headsUpStudy.ModelView

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel : ViewModel(){

    fun login(email: String, password: String, context: Context){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
            .addOnFailureListener{ exception ->
                Toast.makeText(context, "Login failed: ${exception.message}", Toast.LENGTH_LONG).show()
            }
    }
}