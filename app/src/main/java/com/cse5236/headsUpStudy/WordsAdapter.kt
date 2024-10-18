package com.cse5236.headsUpStudy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WordsAdapter(private val viewModel: NewCategoryViewModel) :
    RecyclerView.Adapter<WordsAdapter.ViewHolder>() {

        private var wordsList: List<String> = listOf()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val wordVal= view.findViewById<TextView>(R.id.word_text)
        val deleteButton = itemView.findViewById<Button>(R.id.delete_button)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.word, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.wordVal.text = wordsList[position]
        viewHolder.deleteButton.setOnClickListener{
            viewModel.deleteWord(wordsList[position])
        }
    }

    override fun getItemCount(): Int {
        return wordsList.size
    }

    fun updateWords(words: List<String>){
        wordsList = words
        notifyDataSetChanged()
    }

}