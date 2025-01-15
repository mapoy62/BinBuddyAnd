package com.oym.binbuddy.ui.adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.binbuddy.oym.data.remote.model.InstagramProfile
import com.binbuddy.oym.databinding.ItemInstagramProfileBinding
import com.bumptech.glide.Glide

class InstagramProfilesAdapter(
    private val items: List<InstagramProfile>
) : RecyclerView.Adapter<InstagramProfilesAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemInstagramProfileBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(profile: InstagramProfile) {
            binding.tvUsername.text = profile.username
            binding.tvCategory.text = profile.category

            Glide.with(binding.root.context)
                .load(profile.imageUrl)
                .circleCrop()
                .into(binding.ivProfileImage)

            binding.btnOpenInstagram.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(profile.urlPage))
                binding.root.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemInstagramProfileBinding.inflate(
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
