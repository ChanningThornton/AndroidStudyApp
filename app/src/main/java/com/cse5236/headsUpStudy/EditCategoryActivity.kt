package com.cse5236.headsUpStudy

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

class EditCategoryActivity : AppCompatActivity(), OnClickListener {
    private val editCategoryViewModel: EditCategoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_category)

        val recyclerView = findViewById<RecyclerView>(R.id.word_table)
        val adapter = WordsAdapter(editCategoryViewModel)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        editCategoryViewModel.words.observe(this) { words ->
            adapter.updateWords(words)
        }

        val categoryName = findViewById<EditText>(R.id.editCategory_name)
        editCategoryViewModel.categoryName.observe(this){ name ->
            categoryName.setText(name)
        }

        FirebaseAuth.getInstance().currentUser?.uid?.let {
            editCategoryViewModel.loadCategory(it)
        }


        val newButton = findViewById<Button>(R.id.add_button)
        newButton.setOnClickListener(this)

        val backButton = findViewById<Button>(R.id.back_button)
        backButton.setOnClickListener(this)

        val saveCatButton = findViewById<Button>(R.id.saveCat_button)
        saveCatButton.setOnClickListener(this)

        val deleteCatButton = findViewById<Button>(R.id.deleteCat_button)
        deleteCatButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.back_button -> {
                finish()
            }
            R.id.add_button -> {
                val newWord = findViewById<EditText>(R.id.word_input).text.toString()
                if(newWord.isNotEmpty()){
                    editCategoryViewModel.addWord(newWord)
                    findViewById<EditText>(R.id.word_input).text.clear()
                }
            }
            R.id.saveCat_button -> {
                val name = findViewById<EditText>(R.id.editCategory_name).text.toString()
                val words = editCategoryViewModel.words.value ?: emptyList()
                if(name.isNotEmpty() && words.isNotEmpty()){
                    editCategoryViewModel.updateCategory(name, this)
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Category must have name and at least one word.", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.deleteCat_button -> {
                editCategoryViewModel.deleteCategory(this)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

            else -> Log.e("LoginActivity", "Error: Invalid button press")
        }
    }
}