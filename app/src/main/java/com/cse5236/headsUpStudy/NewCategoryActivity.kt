package com.cse5236.headsUpStudy

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

class NewCategoryActivity : AppCompatActivity(), OnClickListener {
    private val newCategoryViewModel: NewCategoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_category)

        val recyclerView = findViewById<RecyclerView>(R.id.word_table)
        val adapter = WordsAdapter(newCategoryViewModel)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        newCategoryViewModel.words.observe(this) { words ->
            adapter.updateWords(words)
        }

        val newButton = findViewById<Button>(R.id.add_button)
        newButton.setOnClickListener(this)
        val createButton = findViewById<Button>(R.id.createCat_button)
        createButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.add_button -> {
                val newWord = findViewById<EditText>(R.id.word_input).text.toString()
                if(newWord.isNotEmpty()){
                    newCategoryViewModel.addWord(newWord)
                    findViewById<EditText>(R.id.word_input).text.clear()
                }
            }
            R.id.createCat_button -> {
                val name = findViewById<EditText>(R.id.category_name).text.toString()
                val words = newCategoryViewModel.words.value ?: emptyList()
                if(name.isNotEmpty() && words.isNotEmpty()){
                    newCategoryViewModel.saveCategory(name,words, this)
                    val intent = Intent(this, CategoriesActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Category must have name and at least one word.", Toast.LENGTH_SHORT).show()
                }

            }
            else -> Log.e("LoginActivity", "Error: Invalid button press")
        }
    }


}