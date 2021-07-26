package com.dobrucali.gorillas.utils

import com.apollographql.apollo.api.Response
import com.dobrucali.gorillas.data.api.AppException
import com.dobrucali.gorillas.data.entity.Resource

const val API_REQUEST_ERROR = 1000

inline fun <reified T> getDataFromResponse(response: Response<T>): Resource<T> {
    return if (response.hasErrors().not() && response.data is T) {
        val data = response.data as T
        Resource.success(data)
    } else {
        try {
            Resource.error(
                AppException(
                    title = "Error",
                    message = response.errors?.joinToString(),
                    code = API_REQUEST_ERROR
                )
            )
        } catch (cause: Throwable) {
            Resource.error(
                AppException(
                    title = "${cause.message}",
                    message = "${response.errors?.firstOrNull()}",
                    code = API_REQUEST_ERROR
                )
            )
        }
    }
}