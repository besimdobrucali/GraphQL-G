package com.dobrucali.gorillas.data.entity

import com.dobrucali.gorillas.data.api.AppException

data class Resource<out T>(
    val status: Status,
    val data: T?,
    val error: AppException?,
    val exception: Throwable?
) {

    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, error = null, exception = null)
        }

        fun <T> error(error: AppException, data: T? = null): Resource<T> {
            return Resource(Status.ERROR, data, error, exception = null)
        }

        fun <T> exception(exception: Throwable?, data: T? = null): Resource<T> {
            return Resource(Status.ERROR, data, error = null, exception)
        }

    }

}

internal fun Resource<Any>.isSuccess(): Boolean {
    return this.status == Status.SUCCESS
}