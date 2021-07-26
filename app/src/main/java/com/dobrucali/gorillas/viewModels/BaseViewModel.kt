package com.dobrucali.gorillas.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.dobrucali.gorillas.data.entity.Status

abstract class BaseViewModel() : ViewModel() {

    fun onScreenCreated() {}

    protected val _status = MutableLiveData<Status>()
    val status: LiveData<Status>
        get() = _status

    val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    val isStatusLoading = Transformations.map(status){
        if (it == null){
            false
        } else {
            it == Status.LOADING
        }
    }

    override fun onCleared() {
        super.onCleared()
        _status.value = null
    }

}