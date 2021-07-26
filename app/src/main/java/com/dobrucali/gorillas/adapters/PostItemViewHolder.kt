package com.dobrucali.gorillas.adapters

import androidx.recyclerview.widget.RecyclerView
import com.dobrucali.gorillas.PostListQuery
import com.dobrucali.gorillas.databinding.ItemPostBinding

class PostItemViewHolder(private var binding: ItemPostBinding)
    : RecyclerView.ViewHolder(binding.root) {
        fun bind(postItem: PostListQuery.Data1) {
            binding.postItem = postItem
            binding.executePendingBindings()
        }
    }