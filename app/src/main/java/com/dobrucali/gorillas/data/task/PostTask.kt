package com.dobrucali.gorillas.data.task

import com.dobrucali.gorillas.PostListQuery
import com.dobrucali.gorillas.data.entity.Resource
import com.dobrucali.gorillas.data.repository.PostRepository

class PostTask(
    private val postRepository: PostRepository
) {

    suspend fun getAllPosts(): Resource<PostListQuery.Data>{
        return postRepository.getAllPosts()
    }
}