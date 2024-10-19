package com.cse5236.headsUpStudy.ModelView

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class SignUpViewModel : ViewModel(){

    fun signUp(email: String, password: String){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
    }
}