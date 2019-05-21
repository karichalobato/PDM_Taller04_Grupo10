package com.lobato.pdm_taller04_grupo10.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Query
import com.lobato.pdm_taller04_grupo10.database.entities.Libro
import com.lobato.pdm_taller04_grupo10.database.entities.Tags

interface TagsJoinLibroDAO {
    @Query("SELECT * FROM Tags INNER JOIN TagsXLibro ON Tags.idTags = TagsXLibro.tagsID WHERE TagsXLibro.bookID=:tagsID")
    fun getTagsOfBooks(tagsID:Int):LiveData<List<Tags>>

    @Query("SELECT * FROM Book INNER JOIN TagsXLibro ON Book.idBook = TagsXLibro.bookID WHERE TagsXLibro.bookID=:bookID")
    fun getBooksOfTags(bookID:Int):LiveData<List<Libro>>
}