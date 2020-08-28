package com.sample.assignment.retrofit

import com.sample.assignment.db.data.MainList
import retrofit2.Response
import retrofit2.http.GET

/**
Created by Ajay Arya on 28/08/20
 */
interface AllApi {

    @GET("/s/2iodh4vg0eortkl/facts.json")
    suspend fun getList(): Response<MainList>
}