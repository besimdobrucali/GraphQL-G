package com.dobrucali.gorillas.data.repository

import com.apollographql.apollo.coroutines.await
import com.dobrucali.gorillas.PostListQuery
import com.dobrucali.gorillas.data.api.PostApi
import com.dobrucali.gorillas.data.entity.Resource
import com.dobrucali.gorillas.utils.getDataFromResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostRepository(
    private val postApi: PostApi
) {

    suspend fun getAllPosts(): Resource<PostListQuery.Data> {
        val apolloClient = postApi.getApolloClient()
        return withContext(Dispatchers.IO) {
            val response = apolloClient.query(PostListQuery()).await()
            getDataFromResponse(response)
        }
    }

}