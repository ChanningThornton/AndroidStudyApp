package com.cse5236.headsUpStudy.ModelView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.cse5236.headsUpStudy.R

class CategoryAdapter(private val viewModel: CategoriesViewModel) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

        private var categoryList: List<Pair<String, String>> = listOf()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val categoryButton = view.findViewById<Button>(R.id.category_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.category, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.categoryButton.text = categoryList[position].second
    }

    fun updateCategories(categories: List<Pair<String, String>>){
        categoryList = categories
        notifyDataSetChanged()
    }

}