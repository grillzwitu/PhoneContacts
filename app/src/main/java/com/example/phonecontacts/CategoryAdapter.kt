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
            if (position == 0){
                Toast.makeText(holder.itemView.context, "You just clicked Family", Toast.LENGTH_LONG).show()
            }
            if (position == 1){
                Toast.makeText(holder.itemView.context, "You just clicked Friends", Toast.LENGTH_LONG).show()
            }
            if (position == 2){
                Toast.makeText(holder.itemView.context, "You just clicked Work", Toast.LENGTH_LONG).show()
            }
            if (position == 3){
                Toast.makeText(holder.itemView.context, "You just clicked School", Toast.LENGTH_LONG).show()
            }

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