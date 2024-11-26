package com.cse5236.headsUpStudy.ModelView

import androidx.lifecycle.MutableLiveData
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.firestore

object FirebaseRepository {

    private val auth = FirebaseAuth.getInstance();
    private val _categories = MutableLiveData<MutableList<DocumentSnapshot>>(mutableListOf())
    val categories: MutableLiveData<MutableList<DocumentSnapshot>> get() = _categories

    init {
        auth.addAuthStateListener { firebaseAuth ->
            val currentUser = firebaseAuth.currentUser
            if (currentUser != null) {
                loadCategories()
            } else {
                _categories.value = mutableListOf()
            }
        }
    }

    fun getCategory(id: String):DocumentSnapshot? {
        return categories.value?.find { it.id == id }
    }

    fun loadCategories(){
        Firebase.firestore.collection("categories")
            .whereEqualTo("userId", auth.currentUser?.uid)
            .get()
            .addOnSuccessListener { items ->
                if(!items.isEmpty) {
                    _categories.value = items.documents
                } else {
                    _categories.value = mutableListOf()
                }
            }
            .addOnFailureListener {
                _categories.value = mutableListOf() // Clear on failure to avoid showing stale data
            }
    }
}