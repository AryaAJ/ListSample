package com.sample.assignment.util


import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("code")
    val code: String,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("service_version")
    val serviceVersion: String
)