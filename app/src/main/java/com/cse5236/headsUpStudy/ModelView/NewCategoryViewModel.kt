package com.cse5236.headsUpStudy.ModelView

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

class NewCategoryViewModel : ViewModel(), CategoryViewModel {

    private val _words = MutableLiveData<MutableList<String>>(mutableListOf())
    val words: MutableLiveData<MutableList<String>> get() = _words
    private val repository = FirebaseRepository

    override fun addWord(word: String) {
        _words.value?.let {
            it.add(word)
            _words.value = it
        }
    }

    override fun deleteWord(word: String) {
        _words.value?.let {
            it.remove(word)
            _words.value = it
        }
    }

    fun saveCategory(name: String, context: Context){
        val category = hashMapOf(
            "name" to name,
            "userId" to FirebaseAuth.getInstance().currentUser?.uid,
            "words" to words.value
        )
        Firebase.firestore.collection("categories")
            .add(category)
            .addOnSuccessListener {
                repository.loadCategories()
                Toast.makeText(context, "Category saved.", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(context, "Error saving category: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

}