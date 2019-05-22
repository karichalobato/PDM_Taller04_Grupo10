package com.lobato.pdm_taller04_grupo10.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Author")
 data class Autor(

    @ColumnInfo(name = "Name")
    val name: String,
    @ColumnInfo(name = "País de origen")
    val paisOrigen: String
){
    @PrimaryKey(autoGenerate = true)
    val idAuthor: Long = 0
}
