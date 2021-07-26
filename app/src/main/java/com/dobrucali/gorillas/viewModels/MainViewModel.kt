package com.dobrucali.gorillas.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.dobrucali.gorillas.PostListQuery
import com.dobrucali.gorillas.data.entity.Status
import com.dobrucali.gorillas.data.task.PostTask
import kotlinx.coroutines.launch

class MainViewModel(
    private val postTask: PostTask
) : BaseViewModel() {

    private val _postListQueryData = MutableLiveData<PostListQuery.Data>()
    val postListQueryData: LiveData<PostListQuery.Data>
        get() = _postListQueryData

    init {
        getPosts()
    }

    val postList = Transformations.map(postListQueryData) {
        it.posts?.data ?: listOf<PostListQuery.Data1>()
    }

    private fun getPosts() {
        viewModelScope.launch {
            try {
                _status.value = Status.LOADING
                val result = postTask.getAllPosts()
                _status.value = result.status
                when (result.status) {
                    Status.SUCCESS -> {
                        result.data?.let { data ->
                            _postListQueryData.value = data
                        }
                    }
                    Status.ERROR -> {
                        _errorMessage.value = result.error?.message
                    }
                    else -> {}
                }
            } catch (error: Exception) {
                _status.value = Status.ERROR
                _errorMessage.value = error.message
            }
        }
    }

}