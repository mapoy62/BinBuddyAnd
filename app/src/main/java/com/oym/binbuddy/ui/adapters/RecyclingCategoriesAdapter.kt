package com.oym.binbuddy.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oym.binbuddy.data.remote.model.RecyclingCategory
import com.oym.binbuddy.databinding.ItemRecyclingCategoryBinding

class RecyclingCategoriesAdapter(
    private val categories: List<RecyclingCategory>,
    private val onCategoryClicked: (RecyclingCategory) -> Unit
) : RecyclerView.Adapter<RecyclingCategoriesAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemRecyclingCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(category: RecyclingCategory) {
            binding.tvCategoryName.text = category.category
            binding.tvCategoryDescription.text = category.description
            binding.root.setOnClickListener {
                onCategoryClicked(category)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecyclingCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    override fun getItemCount(): Int = categories.size
}

