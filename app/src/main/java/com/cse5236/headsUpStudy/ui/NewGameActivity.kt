package com.cse5236.headsUpStudy.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.cse5236.headsUpStudy.ModelView.CategoriesViewModel
import com.cse5236.headsUpStudy.R
import com.google.firebase.auth.FirebaseAuth

class NewGameActivity : AppCompatActivity(), View.OnClickListener {
    private val categoriesViewModel: CategoriesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.newgame)


        val timeSpinner = findViewById<Spinner>(R.id.time_dropdown)
        ArrayAdapter.createFromResource(
            this,
            R.array.times_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            timeSpinner.adapter = adapter
        }

        val categorySpinner = findViewById<Spinner>(R.id.category_dropdown)
        categoriesViewModel.categories.observe(this) { categories ->
            val categoryList = categories.map {it.second}
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categoryList)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            categorySpinner.adapter = adapter
        }

        FirebaseAuth.getInstance().currentUser?.uid?.let {
            categoriesViewModel.loadCategory(it)
        }


        Log.d("NewGameActivity", "onCreate called")

        val backButton = findViewById<Button>(R.id.back)
        backButton.setOnClickListener(this)

        val startButton = findViewById<Button>(R.id.start_button)
        startButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id){
            R.id.back -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            R.id.start_button -> {
                val intent = Intent(this, GameActivity::class.java)
                val categoryPosition = findViewById<Spinner>(R.id.category_dropdown).selectedItemPosition
                if(categoryPosition > -1) {
                    val categoryId =
                        categoriesViewModel.categories.value?.get(categoryPosition)?.first
                    val timeLimit =
                        findViewById<Spinner>(R.id.time_dropdown).selectedItem.toString()
                    intent.putExtra("CATEGORY_ID", categoryId)
                    intent.putExtra("TIME_LIMIT", timeLimit)
                    startActivity(intent)
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("NewGameActivity", "onStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.d("NewGameActivity", "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.d("NewGameActivity", "onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.d("NewGameActivity", "onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("NewGameActivity", "onDestroy called")
    }


}