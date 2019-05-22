package com.lobato.pdm_taller04_grupo10.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(tableName = "TagsXLibro",
    primaryKeys = arrayOf("tagsID","bookID"),
    foreignKeys = [ForeignKey(entity = Tags::class,
        parentColumns = ["idTags"],childColumns = ["id_tags"]),
    ForeignKey(entity = Libro::class,parentColumns = ["idBook"],childColumns = ["id_book"])])

data class TagsXLibro (var tagsID:Int,var bookID:Int)
