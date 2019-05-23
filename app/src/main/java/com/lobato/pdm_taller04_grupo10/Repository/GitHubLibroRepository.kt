package com.lobato.pdm_taller04_grupo10.Repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.lobato.pdm_taller04_grupo10.database.daos.LibroDAO
import com.lobato.pdm_taller04_grupo10.database.entities.Libro

class GitHubLibroRepository(private val bookDao: LibroDAO){

    @WorkerThread
    suspend fun insert(repo: Libro){
        bookDao.insertLibro(repo)
    }

    fun getAll(): LiveData<List<Libro>> = bookDao.getAllBooks()

    fun delete()= bookDao.deleteBooks()


}