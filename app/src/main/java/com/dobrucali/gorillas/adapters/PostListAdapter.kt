package com.dobrucali.gorillas.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import com.dobrucali.gorillas.PostListQuery
import com.dobrucali.gorillas.databinding.ItemPostBinding

class PostListAdapter(
    private val clickListener: (PostListQuery.Data1) -> Unit
) : PagedListAdapter<PostListQuery.Data1, PostItemViewHolder>(PostDiffCallBack)  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostItemViewHolder {
        return PostItemViewHolder(ItemPostBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PostItemViewHolder, position: Int) {
        val postItem = getItem(position)
        postItem?.let {
            holder.itemView.setOnClickListener {
                clickListener.invoke(postItem)
            }
            holder.bind(postItem)
        }
    }

}