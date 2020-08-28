package com.sample.assignment.util

/**
Created by Ajay Arya on 28/08/20
 */

/**
 *     To  get the data if API request succeeded
 *
 * @param T
 */
sealed class ApiResponse<out T> {
    data class Success<out T>(val successData: T) : ApiResponse<T>()
    class Error(
        val exception: java.lang.Exception
    ) : ApiResponse<Nothing>()
}