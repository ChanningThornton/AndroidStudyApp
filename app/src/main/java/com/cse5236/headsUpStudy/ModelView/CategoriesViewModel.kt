package com.cse5236.headsUpStudy.ModelView

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class CategoriesViewModel : ViewModel() {
    private val _categories = MutableLiveData<MutableList<Pair<String, String>>>(mutableListOf())
    val categories: MutableLiveData<MutableList<Pair<String, String>>> get() = _categories

    fun loadCategory(uid: String){
        Firebase.firestore.collection("categories")
            .whereEqualTo("userId", uid)
            .get()
            .addOnSuccessListener { items ->
                if(!items.isEmpty) {
                    val documents = items.documents.map { document ->
                        Pair(document.id, document.getString("name") ?: "")
                    }
                    _categories.value = documents.toMutableList()
                } else {
                    _categories.value = mutableListOf()
                }
            }
    }
}