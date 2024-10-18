package com.cse5236.headsUpStudy

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.tasks.await

class FirebaseAccountService {
    private var auth: FirebaseAuth = Firebase.auth

    suspend fun login(email: String, password: String){
        auth.signInWithEmailAndPassword(email, password).await()
    }

    suspend fun signUp(email: String, password: String){
        auth.createUserWithEmailAndPassword(email, password).await()
    }

    fun logout(){
        auth.signOut()
    }

    fun loggedIn(): Boolean {
        return auth.currentUser != null
    }

}