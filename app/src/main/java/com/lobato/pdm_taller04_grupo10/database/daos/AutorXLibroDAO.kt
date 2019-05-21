package com.lobato.pdm_taller04_grupo10.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Query
import com.lobato.pdm_taller04_grupo10.database.entities.Autor
import com.lobato.pdm_taller04_grupo10.database.entities.Libro

interface AutorXLibroDAO {

    @Query("SELECT * FROM Author INNER JOIN AutorXLibro ON Author.idAuthor = AutorXLibro.authorID WHERE AutorXLibro.bookID=:authorID")
    fun getAuthorOfBooks(authorID: Int): LiveData<List<Autor>>

    @Query("SELECT * FROM Book INNER JOIN AutorXLibro ON Book.idBook = AutorXLibro.bookID WHERE AutorXLibro.bookID=:bookID")
    fun getBooksOfAuthors(bookID:Int):LiveData<List<Libro>>
}