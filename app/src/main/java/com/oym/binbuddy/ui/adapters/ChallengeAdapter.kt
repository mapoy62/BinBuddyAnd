package com.oym.binbuddy.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oym.binbuddy.data.remote.model.Challenge
import com.oym.binbuddy.databinding.ItemChallengeBinding

class ChallengesAdapter(
    private val items: List<Challenge>,
    private val onItemClicked: (Challenge) -> Unit
) : RecyclerView.Adapter<ChallengesAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemChallengeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Challenge) {
            binding.tvChallengeName.text = item.name
            binding.tvChallengeDescription.text = item.description

            Glide.with(binding.root.context)
                .load(item.imgUrl)
                .circleCrop()
                .into(binding.ivChallengeImage)

            binding.root.setOnClickListener {
                onItemClicked(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemChallengeBinding.inflate(
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