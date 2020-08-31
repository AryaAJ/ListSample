package com.sample.assignment.repository.impl

import android.app.Application
import com.sample.assignment.db.DataDao
import com.sample.assignment.db.data.MainList
import com.sample.assignment.repository.Repository
import com.sample.assignment.retrofit.AllApi
import com.sample.assignment.util.APIResponseHandling.handleApiError
import com.sample.assignment.util.ApiResponse
import com.sample.assignment.util.NetworkManager.isOnline
import com.sample.assignment.util.noDataFound
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
Created by Ajay Arya on 28/08/20
 */
class ListRepositoryImpl(
    private val api: AllApi,
    private val context: Application,
    private val dao: DataDao
) :
    Repository {

    /**
     *      If Internet connectivity available fetched data from Search Venue API else it will fetch data from database
     *      In case if there is no data in Database then Toast will be displayed
     */
    override suspend fun getList(): ApiResponse<MainList> {
        if (isOnline(context)) {
            return try {
                val response = api.getList()
                if (response.isSuccessful) {
                    //save the data
                    response.body()?.let {
                        withContext(Dispatchers.IO) {
                            if (it != null) {
                                dao.addList(it)
                            } else
                                handleApiError(response)
                        }
                    }
                    getListfromDB()
                } else {
                    handleApiError(response)
                }
            } catch (e: Exception) {
                ApiResponse.Error(e)
            }
        } else {
            return getListfromDB()
        }
    }

    override suspend fun getListfromDB(): ApiResponse<MainList> {
        return withContext(Dispatchers.IO) {
            val mainList = dao.getList()
            if (mainList!=null && mainList.rows!=null && !mainList.rows.isNullOrEmpty())
                ApiResponse.Success(dao.getList())
            else {
                context.noDataFound()
            }
        }
    }
}