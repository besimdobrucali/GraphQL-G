package com.dobrucali.gorillas.adapters

import androidx.recyclerview.widget.DiffUtil
import com.dobrucali.gorillas.PostListQuery

object PostDiffCallBack : DiffUtil.ItemCallback<PostListQuery.Data1>() {
    override fun areItemsTheSame(oldItem: PostListQuery.Data1, newItem: PostListQuery.Data1): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PostListQuery.Data1, newItem: PostListQuery.Data1): Boolean {
        return oldItem == newItem
    }
}