package com.sample.assignment.util

import android.content.Context
import com.sample.assignment.R


/**
Created by Ajay Arya on 28/08/20
 */
const val BASEURL: String = "https://dl.dropboxusercontent.com/"

fun Context.noDataFound(): ApiResponse.Error {
    return ApiResponse.Error(Exception(this.resources.getString(R.string.no_data_found)))
}