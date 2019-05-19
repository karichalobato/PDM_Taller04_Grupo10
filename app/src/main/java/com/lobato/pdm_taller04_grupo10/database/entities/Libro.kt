package com.lobato.pdm_taller04_grupo10.database.entities

import android.media.Image
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Libro")
data class Libro (
    @ColumnInfo(name = "Portada")
    val portada: Image,
    @ColumnInfo(name = "Titulo")
    val name: String,
    @ColumnInfo(name = "Autor")
    val autor: String,
    @ColumnInfo(name = "Edición")
    val edicion: String,
    @ColumnInfo(name = "Descripción")
    val descripcion: String
){
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}