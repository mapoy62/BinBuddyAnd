package com.oym.binbuddy.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oym.binbuddy.data.remote.model.Tip
import com.oym.binbuddy.databinding.ItemTipBinding

class TipsAdapter(
    private val tips: MutableList<Tip>
) : RecyclerView.Adapter<TipsAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemTipBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tip: Tip) {
            binding.tvTipContent.text = tip.content
            binding.tvCreatedAt.text = tip.createdAt
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTipBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tips[position])
    }

    override fun getItemCount(): Int = tips.size
}