package com.cse5236.headsUpStudy.ModelView

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class GameViewModel: ViewModel() {

    private val _words = MutableLiveData<MutableList<String>>(mutableListOf())
    val words: MutableLiveData<MutableList<String>> get() = _words

    fun loadCategory(id: String?){
        Firebase.firestore.collection("categories")
            .document(id ?: "")
            .get()
            .addOnSuccessListener { item ->
                val words = item.get("words") as? List<String>
                _words.value = words?.toMutableList()

            }
    }
}