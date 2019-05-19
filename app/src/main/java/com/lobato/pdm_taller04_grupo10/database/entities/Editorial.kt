package com.lobato.pdm_taller04_grupo10.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Editorial")
data class Editorial (
    @ColumnInfo(name = "Nombre")
    val name: String,
    @ColumnInfo(name = "Address")
    val addres: String,
    @ColumnInfo(name = "PostalCode")
    val postal_code: Int,
    @ColumnInfo(name = "Phone")
    val phone: Number,
    @ColumnInfo(name = "Email")
    val email: String
){
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}