package com.oym.binbuddy.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oym.binbuddy.R
import com.oym.binbuddy.data.remote.model.Reward
import com.oym.binbuddy.databinding.ItemRewardBinding

class RewardsAdapter : RecyclerView.Adapter<RewardsAdapter.RewardViewHolder>() {

    private val rewards = mutableListOf<Reward>()

    fun submitList(newRewards: List<Reward>) {
        rewards.clear()
        rewards.addAll(newRewards)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RewardViewHolder {
        val binding = ItemRewardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RewardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RewardViewHolder, position: Int) {
        holder.bind(rewards[position])
    }

    override fun getItemCount() = rewards.size

    inner class RewardViewHolder(private val binding: ItemRewardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(reward: Reward) {
            binding.rewardName.text = reward.name
            binding.rewardDescription.text = reward.description
            /*
            Glide.with(binding.root.context)
                .load(reward.imageUrl)
                .placeholder(R.drawable.placeholder_image) // Imagen por defecto mientras carga
                .error(R.drawable.placeholder_image) // Imagen en caso de fallo
                .override(300, 300) // Redimensiona la imagen a 300x300 píxeles
                .centerCrop() // Recorta la imagen para que se ajuste
                .into(binding.rewardImage)

             */
        }
    }
}
