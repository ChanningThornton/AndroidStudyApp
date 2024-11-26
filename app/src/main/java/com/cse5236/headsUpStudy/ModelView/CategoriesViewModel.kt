package com.cse5236.headsUpStudy.ModelView

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class CategoriesViewModel() : ViewModel() {
    private val repository = FirebaseRepository
    val categories: LiveData<MutableList<Pair<String, String>>> = repository.categories.map { documents ->
        documents.map { document ->
            Pair(document.id, document.getString("name") ?: "")
        }.toMutableList()
    }

}