package com.dobrucali.gorillas.data.repository

import com.apollographql.apollo.api.Input
import com.apollographql.apollo.coroutines.await
import com.dobrucali.gorillas.PostListQuery
import com.dobrucali.gorillas.PostQuery
import com.dobrucali.gorillas.data.api.PostApi
import com.dobrucali.gorillas.data.entity.Resource
import com.dobrucali.gorillas.type.PageQueryOptions
import com.dobrucali.gorillas.type.PaginateOptions
import com.dobrucali.gorillas.utils.getDataFromResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostRepository(
    private val postApi: PostApi
) {

    suspend fun getPostById(postId: String): Resource<PostQuery.Data> {
        val apolloClient = postApi.getApolloClient()
        return withContext(Dispatchers.IO) {
            val response = apolloClient.query(PostQuery(postId)).await()
            getDataFromResponse(response)
        }
    }

    suspend fun getPostsWithPageNumber(page: Int, limit: Int): Resource<PostListQuery.Data> {
        val apolloClient = postApi.getApolloClient()
        return withContext(Dispatchers.IO) {
            val response = apolloClient.query(
                PostListQuery(
                    Input.optional(
                        PageQueryOptions(
                                paginate = Input.optional(
                                    PaginateOptions(
                                    page = Input.optional(page),
                                    limit = Input.optional(limit)
                                )
                            )
                        )
                    )
                )
            ).await()
            getDataFromResponse(response)
        }
    }

}