package com.lobato.pdm_taller04_grupo10.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Author")
data class Autor(

    @ColumnInfo(name = "Name")
    val name: String,
    @ColumnInfo(name = "Nationality")
    val nationality: String
)
{
    @PrimaryKey(autoGenerate = true)
    var idAuthor: Long = 0
}