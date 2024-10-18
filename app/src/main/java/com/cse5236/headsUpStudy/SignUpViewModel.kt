package com.cse5236.headsUpStudy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel(){

    fun signUp(email: String, password: String){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
    }
}