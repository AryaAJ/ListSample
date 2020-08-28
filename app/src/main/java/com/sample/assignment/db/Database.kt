package com.sample.assignment.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sample.assignment.db.data.ListConverter
import com.sample.assignment.db.data.MainList

/**
Created by Ajay Arya on 28/08/20
 */

@Database(
    entities = [MainList::class], version = 1, exportSchema = false
)
@TypeConverters(
    ListConverter::class
)
abstract class Database : RoomDatabase() {
    abstract val venueDao: DataDao
}