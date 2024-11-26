package com.cse5236.headsUpStudy.ModelView

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class SignUpViewModel : ViewModel(){

    fun signUp(email: String, password: String, context: Context){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnFailureListener{ exception ->
                Toast.makeText(context, "Sign up failed: ${exception.message}", Toast.LENGTH_LONG).show()
            }
    }
}