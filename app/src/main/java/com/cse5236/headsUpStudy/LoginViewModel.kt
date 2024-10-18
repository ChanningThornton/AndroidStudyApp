package com.cse5236.headsUpStudy

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel : ViewModel(){

    fun login(email: String, password: String){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
    }
}