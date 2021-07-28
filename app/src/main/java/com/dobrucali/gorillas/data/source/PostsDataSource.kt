package com.dobrucali.gorillas.data.source

import android.util.Log
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.dobrucali.gorillas.PostListQuery
import com.dobrucali.gorillas.data.entity.isSuccess
import com.dobrucali.gorillas.data.task.PostTask
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlin.math.ceil

class PostsDataSource(
    private val scope: CoroutineScope,
    private val postTask: PostTask,
    private val config: PagedList.Config
) :
    PageKeyedDataSource<String, PostListQuery.Data1>() {

    private var currentPage : Int = 1
    private var maxPageLimit : Int = Int.MAX_VALUE

    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<String, PostListQuery.Data1>
    ) {
        scope.launch {
            try {
                val response = postTask.getPostsWithPageNumber(currentPage, config.pageSize)
                when{
                    response.isSuccess() -> {
                        val listing = response.data?.posts
                        listing?.meta?.totalCount?.let {  totalCount ->
                            maxPageLimit = ceil(totalCount.toDouble() / config.pageSize).toInt()
                        }
                        val posts = listing?.data?.filterNotNull()
                        callback.onResult(posts ?: listOf(), currentPage.toString(), currentPage.plus(1).toString())
                    }
                }

            } catch (exception : Exception){
                Log.e("PostsDataSource", "Failed to fetch data!")
            }
        }
    }

    override fun loadBefore(
        params: LoadParams<String>,
        callback: LoadCallback<String, PostListQuery.Data1>
    ) {
        scope.launch {
            try {
                if (currentPage > 1){
                    val response = postTask.getPostsWithPageNumber(currentPage.minus(1), config.pageSize)
                    when{
                        response.isSuccess() -> {
                            currentPage = currentPage.minus(1)
                            val listing = response.data?.posts
                            val posts = listing?.data?.filterNotNull()
                            callback.onResult(posts ?: listOf(), currentPage.toString())
                        }
                    }
                }
            } catch (exception : Exception){
                Log.e("PostsDataSource", "Failed to fetch data!")
            }
        }
    }

    override fun loadAfter(
        params: LoadParams<String>,
        callback: LoadCallback<String, PostListQuery.Data1>
    ) {
        scope.launch {
            try {
                if (currentPage < maxPageLimit) {
                    val response = postTask.getPostsWithPageNumber(currentPage.plus(1), config.pageSize)
                    when{
                        response.isSuccess() -> {
                            currentPage = currentPage.plus(1)
                            val listing = response.data?.posts
                            val posts = listing?.data?.filterNotNull()
                            callback.onResult(posts ?: listOf(), currentPage.toString())
                        }
                    }
                }
            } catch (exception : Exception){
                Log.e("PostsDataSource", "Failed to fetch data!")
            }
        }
    }

    override fun invalidate() {
        super.invalidate()
        scope.cancel()
    }

}