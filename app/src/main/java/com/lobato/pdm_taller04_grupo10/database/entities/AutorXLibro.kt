package com.lobato.pdm_taller04_grupo10.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey


@Entity(tableName = "AutorXLibro",
    primaryKeys = arrayOf("authorID","bookID"),
    foreignKeys = [ForeignKey(entity = Autor::class,
    parentColumns = ["idAuthor"], childColumns = ["ID_AUTHOR"]),
        ForeignKey(entity = Libro::class,parentColumns = ["idBook"],childColumns = ["ID_BOOK"])])

class AutorXLibro (var authorID:Int, var bookID:Int)