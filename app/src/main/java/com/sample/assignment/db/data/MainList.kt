package com.sample.assignment.db.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "list")
data class MainList(
    @ColumnInfo(name = "rows")
    @SerializedName("rows")
    val rows: List<Row>,
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String
)