package com.lobato.pdm_taller04_grupo10.database.entities

import android.media.Image
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Book")
data class Libro (
    @ColumnInfo(name = "CoverPage")
    val cover_page: Image,
    @ColumnInfo(name = "Title")
    val title: String,
    @ColumnInfo(name = "Author")
    val author: String,
    @ColumnInfo(name = "Edition")
    val edition: String,
    @ColumnInfo(name = "Description")
    val description: String
){
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
