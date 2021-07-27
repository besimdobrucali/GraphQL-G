package com.dobrucali.gorillas.viewModels

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dobrucali.gorillas.PostQuery
import com.dobrucali.gorillas.data.entity.Status
import com.dobrucali.gorillas.data.task.PostTask
import com.dobrucali.gorillas.utils.Constants
import kotlinx.coroutines.launch

class PostDetailViewModel(
    private val postTask: PostTask
): BaseViewModel() {

    private val _post = MutableLiveData<PostQuery.Post>()
    val post: LiveData<PostQuery.Post>
        get() = _post


    fun bindArguments(arguments: Bundle) {
        val postId = arguments.getString(Constants.POST_ID_KEY)
        postId?.let { getPostById(it) }
    }

    private fun getPostById(postId: String) {
        viewModelScope.launch {
            try {
                _status.value = Status.LOADING
                val result = postTask.getPostById(postId)
                _status.value = result.status
                when (result.status) {
                    Status.SUCCESS -> {
                        result.data?.post.let { post ->
                            _post.value = post
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