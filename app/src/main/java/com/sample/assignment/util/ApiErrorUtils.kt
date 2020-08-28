package com.sample.assignment.util

import com.google.gson.GsonBuilder
import retrofit2.Response
import java.io.IOException

/**
Created by Ajay Arya on 28/08/20
 */
object ApiErrorUtils {
    fun parseError(response: Response<*>): ErrorResponse {
        val gson = GsonBuilder().create()
        val error: ErrorResponse
        try {
            error = gson.fromJson(response.errorBody()?.string(), ErrorResponse::class.java)
        } catch (e: IOException) {
            e.message?.let { }
            return ErrorResponse("","","")
        }
        return error
    }
}