package com.sample.assignment.repository

import com.sample.assignment.db.data.MainList
import com.sample.assignment.util.ApiResponse

/**
Created by Ajay Arya on 28/08/20
 */
interface Repository {
    suspend fun getList(): ApiResponse<MainList>
//    suspend fun getAPODListByDate(date: String): ApiResponse<List<MainItem>>
    suspend fun getListfromDB(): ApiResponse<MainList>
}