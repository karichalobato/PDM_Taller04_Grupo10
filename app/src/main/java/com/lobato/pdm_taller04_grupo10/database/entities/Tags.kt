package com.lobato.pdm_taller04_grupo10.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Tags")
data class Tags (

    @ColumnInfo(name = "TagsName")
    val tagsName: String
)
{
    @PrimaryKey(autoGenerate = true)
    var idTags: Long = 0
}