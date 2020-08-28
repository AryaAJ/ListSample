package com.sample.assignment.db.data


import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "rows")
data class Row(
    @ColumnInfo(name = "description")
    @SerializedName("description")
    val description: String,
    @ColumnInfo(name = "imageHref")
    @SerializedName("imageHref")
    val imageHref: String,
    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String
)