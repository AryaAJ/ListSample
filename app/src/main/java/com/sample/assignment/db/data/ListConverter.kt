package com.sample.assignment.db.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListConverter {

    @TypeConverter
    fun fromString(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayListToString(list: List<String>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun stringToRow(json: String): Row {
        val gson = Gson()
        val type = object : TypeToken<Row>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun rowToString(list: Row): String {
        val gson = Gson()
        val type = object : TypeToken<Row>() {}.type
        return gson.toJson(list, type)
    }

    @TypeConverter
    fun stringToRowList(json: String): List<Row> {
        val gson = Gson()
        val type = object : TypeToken<List<Row>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun rowListToString(list: List<Row>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Row>>() {}.type
        return gson.toJson(list, type)
    }
}