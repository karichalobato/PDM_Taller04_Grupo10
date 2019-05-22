package com.lobato.pdm_taller04_grupo10.database.entities


import androidx.room.*


@Entity(indices = [Index("title"), Index("author")],
    tableName = "Book",
    foreignKeys = [ForeignKey(entity = Editorial::class,
        parentColumns = ["idEditorial"], childColumns = ["Editorial"])])
 class Libro (

    @ColumnInfo(name = "CoverPage")
    val cover_page: String,
    @ColumnInfo(name = "Title")
    val title: String,
    @ColumnInfo(name = "Edition")
    val edition: Int,
    @ColumnInfo(name = "Description")
    val description: String,
    @ColumnInfo(name = "Editorial")
    val editorial: Long,
    @ColumnInfo(name = "Favorito")
    val favorito: Boolean,
    @ColumnInfo(name = "ISBN")
    val isbn: String
)
{
    @PrimaryKey(autoGenerate = true)
    val idBook: Long = 0
}



