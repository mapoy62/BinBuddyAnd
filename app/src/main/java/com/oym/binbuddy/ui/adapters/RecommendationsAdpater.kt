package com.oym.binbuddy.ui.adapters

import android.content.Intent
import android.net.Uri

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oym.binbuddy.databinding.ItemRecommendationBinding
import com.bumptech.glide.Glide
import com.oym.binbuddy.data.remote.model.RecommendationItem

class RecommendationsAdapter(
    private val items: List<RecommendationItem>
) : RecyclerView.Adapter<RecommendationsAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemRecommendationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RecommendationItem) {
            binding.tvTitle.text = item.title
            binding.tvAuthor.text = item.author
            binding.tvRating.text = "Rating: ${item.rating}"
            binding.tvDownloads.text = item.downloads

            Glide.with(binding.root.context)
                .load(item.thumbnail)
                .into(binding.ivThumbnail)

            binding.root.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.link))
                binding.root.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecommendationBinding.inflate(
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

