package com.lobato.pdm_taller04_grupo10.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "UserFavorites")//como se llamara nuestra tabla en la BD
data class userFavorites(

    @ColumnInfo(name = "BookName")
    val bookName: String,

    @ColumnInfo(name = "Author")
    val author: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}