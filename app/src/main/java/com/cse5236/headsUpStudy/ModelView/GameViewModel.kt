package com.cse5236.headsUpStudy.ModelView

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.firestore

class GameViewModel: ViewModel() {
    private val repository = FirebaseRepository
    private val _words = MutableLiveData<MutableList<String>>(mutableListOf())
    val words: MutableLiveData<MutableList<String>> get() = _words

    private val _streak = MutableLiveData<Int>()
    val streak: LiveData<Int> get() = _streak

    fun loadCategory(id: String?){
        val document = repository.getCategory(id ?: "")
        val words = document?.get("words") as? List<String>
        _words.value = words?.toMutableList()
    }

    fun getStreak(userId: String){
        Firebase.firestore.collection("streak")
            .whereEqualTo("userID", userId)
            .limit(1)
            .get()
            .addOnSuccessListener { item ->
                val streak: Long
                if(!item.isEmpty) {
                    streak = item.first().get("streak") as Long
                } else {
                    addStreak(userId)
                    streak = 0
                }
                _streak.value = streak.toInt()
            }
    }

    private fun addStreak(userId: String){
        val streak = hashMapOf(
            "userID" to userId,
            "streak" to 0
        )
        Firebase.firestore.collection("streak")
            .add(streak)
    }

    fun updateStreak(userId: String, newStreak: Int){
        Firebase.firestore.collection("streak")
            .whereEqualTo("userID", userId)
            .limit(1)
            .get()
            .addOnSuccessListener { item ->
                val document = Firebase.firestore.collection("streak")
                    .document(item.first().id)
                document.update("streak", newStreak)
            }

    }
}