package com.lobato.pdm_taller04_grupo10.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    indices = [Index("idEditorial")],
    tableName = "Editorial")
data class Editorial (

    @ColumnInfo(name = "Name")
    val name: String,
    @ColumnInfo(name = "Address")
    val addres: String,
    @ColumnInfo(name = "Email")
    val email: String
){
    @PrimaryKey(autoGenerate = true)
    var idEditorial: Long = 0
}