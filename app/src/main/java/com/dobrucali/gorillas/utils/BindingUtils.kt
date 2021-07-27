package com.dobrucali.gorillas.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.dobrucali.gorillas.PostListQuery
import com.dobrucali.gorillas.adapters.PostListAdapter

@BindingAdapter("postListData")
fun bindPostListRecyclerView(recyclerView: RecyclerView, data: PagedList<PostListQuery.Data1>?) {
    val adapter = recyclerView.adapter as PostListAdapter
    adapter.submitList(data)
}

@BindingAdapter("formattedBody")
fun bindFormattedBody(textView: TextView, body: String?) {
    textView.text = if (body != null && body.length > Constants.BODY_CHAR_SIZE){
        body.substring(0, Constants.BODY_CHAR_SIZE)
    } else body ?: ""
}
