package com.oym.binbuddy.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oym.binbuddy.data.remote.model.Tweet
import com.oym.binbuddy.databinding.ItemTweetBinding

class TweetsAdapter(
    private val items: List<Tweet>
) : RecyclerView.Adapter<TweetsAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemTweetBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(tweet: Tweet) {
            binding.tvAuthor.text = tweet.author
            binding.tvText.text = tweet.text
            binding.tvCreatedAt.text = tweet.createdAt
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTweetBinding.inflate(
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