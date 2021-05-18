package com.example.phonecontacts

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.phonecontacts.databinding.CategoryItemsBinding

class CategoryAdapter: RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private val categories: MutableList<Categories> = mutableListOf()

    fun  setUpCategories(categories: List<Categories>) {
        this.categories.addAll(categories)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(CategoryItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.bindItem(category)

        holder.itemView.setOnClickListener {

            val model = categories[position]

            val catName: String = model.viewName
            val catImage: Int = model.viewLogo

            val  intent = Intent(holder.itemView.context, MainActivity::class.java)

            intent.putExtra("iCatName", catName)
            intent.putExtra("icatLogo", catImage)

            holder.itemView.context.startActivity(intent)

        }

    }

    override fun getItemCount(): Int {
        return categories.size
    }

    inner class CategoryViewHolder(private val binding: CategoryItemsBinding):RecyclerView.ViewHolder(binding.root){
        fun  bindItem(category: Categories){
            binding.catImage.setImageResource(category.viewLogo)
            binding.catName.text = category.viewName
        }
    }
}