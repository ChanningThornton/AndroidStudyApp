package com.cse5236.headsUpStudy.ModelView

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class EditCategoryViewModel : ViewModel(), CategoryViewModel {
    private val repository = FirebaseRepository
    private val _words = MutableLiveData<MutableList<String>>(mutableListOf())
    val words: MutableLiveData<MutableList<String>> get() = _words


    private val _categoryName = MutableLiveData<String>()
    val categoryName: LiveData<String> get() = _categoryName

    private var docName = "";

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

    fun loadCategory(id: String?){
        val document = repository.getCategory(id ?: "")
        docName = document?.id ?: "";
        val words = document?.get("words") as? List<String>
        val name = document?.getString("name")
        _words.value = words?.toMutableList()
        _categoryName.value = name
    }

    fun updateCategory(name: String, context: Context){
        val document = Firebase.firestore.collection("categories")
            .document(docName)
        document.update("name", name)
        document.update("words", words.value)
            .addOnSuccessListener {
                repository.loadCategories()
                Toast.makeText(context, "Category updated.", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                    Toast.makeText(context, "Error updating category: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    fun deleteCategory(context: Context){
        Firebase.firestore.collection("categories")
            .document(docName).delete()
            .addOnSuccessListener {
                repository.loadCategories()
                Toast.makeText(context, "Category deleted.", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(context, "Error deleting category: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }


}