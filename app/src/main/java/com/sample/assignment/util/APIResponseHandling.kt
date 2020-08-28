package com.sample.assignment.util

import retrofit2.Response

/**
Created by Ajay Arya on 28/08/20
 */
/**
 *      To get the data if API request succeeded
 *      To get the exception if API request failed.
 *
 */
object APIResponseHandling {
    fun <T : Any> handleApiError(resp: Response<T>): ApiResponse.Error {
        val error = ApiErrorUtils.parseError(resp)
        return ApiResponse.Error(Exception(error.msg))
    }

    fun <T : Any> handleSuccess(response: Response<T>): ApiResponse<T> {
        response.body()?.let {
            return ApiResponse.Success(it)
        } ?: return handleApiError(response)
    }
}