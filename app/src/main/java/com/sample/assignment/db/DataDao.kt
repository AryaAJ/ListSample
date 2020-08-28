package com.sample.assignment.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sample.assignment.db.data.MainList

/**
Created by Ajay Arya on 28/08/20
 */
/**
 *      In this class methods are defined to access database
 *
 */
@Dao
interface DataDao {

    /**
     *      Fetches venue list data from list table
     *
     */
    @Query("SELECT * FROM list")
    fun getList(): MainList

    /**
     *      Insert list
     *      Replace if any row is conflicting
     *
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addList(list: MainList)

}