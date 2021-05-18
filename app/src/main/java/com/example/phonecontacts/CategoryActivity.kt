package com.example.phonecontacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.phonecontacts.databinding.ActivityCategoryBinding

class CategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryBinding
    private val adapter = CategoryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpData(binding)
    }

    private fun setUpData(binding: ActivityCategoryBinding){

        val categories = mutableListOf<Categories>()
        categories.add(
            Categories(
                R.drawable.ic_baseline_family_restroom_24,
                "Family"
            )
        )
        categories.add(
            Categories(
                R.drawable.ic_baseline_emoji_people_24,
                "Friends"
            )
        )
        categories.add(
            Categories(
                R.drawable.ic_baseline_work_24,
                "Work"
            )
        )
        categories.add(
            Categories(
                R.drawable.ic_baseline_school_24,
                "School"
            )
        )

        title = "Contacts: Categories"

        binding.categoriesRecyclerView.adapter = adapter
        binding.categoriesRecyclerView.layoutManager = GridLayoutManager(this, 2)

        adapter.setUpCategories(categories)
    }
}