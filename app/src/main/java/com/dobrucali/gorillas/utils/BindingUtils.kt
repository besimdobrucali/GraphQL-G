package com.dobrucali.gorillas.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dobrucali.gorillas.PostListQuery
import com.dobrucali.gorillas.adapters.PostListAdapter

@BindingAdapter("postListData")
fun bindPostListRecyclerView(recyclerView: RecyclerView, data: List<PostListQuery.Data1>?) {
    val adapter = recyclerView.adapter as PostListAdapter
    adapter.submitList(data ?: listOf())
}
