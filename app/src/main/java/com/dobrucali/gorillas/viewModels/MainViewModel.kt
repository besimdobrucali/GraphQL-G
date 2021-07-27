package com.dobrucali.gorillas.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dobrucali.gorillas.PostListQuery
import com.dobrucali.gorillas.data.source.PostsDataSource
import com.dobrucali.gorillas.data.task.PostTask

class MainViewModel(
    private val postTask: PostTask
) : BaseViewModel() {

    val postListQueryData: LiveData<PagedList<PostListQuery.Data1>>

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(false)
            .build()
        postListQueryData = initializedPagedListBuilder(config).build()
    }

    private fun initializedPagedListBuilder(config: PagedList.Config):
            LivePagedListBuilder<String, PostListQuery.Data1> {

        val dataSourceFactory = object : DataSource.Factory<String, PostListQuery.Data1>() {
            override fun create(): DataSource<String, PostListQuery.Data1> {
                return PostsDataSource(viewModelScope, postTask, config)
            }
        }
        return LivePagedListBuilder(dataSourceFactory, config)
    }

    companion object{
        const val PAGE_SIZE = 10
    }

}