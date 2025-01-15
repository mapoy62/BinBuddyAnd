package com.oym.binbuddy.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oym.binbuddy.data.remote.model.ComingSoonItem
import com.oym.binbuddy.databinding.ItemComingSoonBinding

class ComingSoonAdapter(
    private val items: List<ComingSoonItem>
) : RecyclerView.Adapter<ComingSoonAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemComingSoonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ComingSoonItem) {
            binding.tvName.text = item.name
            Glide.with(binding.root.context)
                .load(item.link)
                .into(binding.ivBanner)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemComingSoonBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}
