package com.cse5236.headsUpStudy

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

class NewCategoryViewModel : ViewModel() {

    private val _words = MutableLiveData<MutableList<String>>(mutableListOf())
    val words: MutableLiveData<MutableList<String>> get() = _words

    fun addWord(word: String) {
        _words.value?.let {
            it.add(word)
            _words.value = it
        }
    }

    fun deleteWord(word: String) {
        _words.value?.let {
            it.remove(word)
            _words.value = it
        }
    }

    fun saveCategory(name: String, words: List<String>, context: Context){
        val category = hashMapOf(
            "name" to name,
            "userId" to FirebaseAuth.getInstance().currentUser?.uid,
            "words" to words
        )
        Firebase.firestore.collection("categories")
            .add(category)
            .addOnSuccessListener {
                Toast.makeText(context, "Category save.", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(context, "Error saving category: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

}