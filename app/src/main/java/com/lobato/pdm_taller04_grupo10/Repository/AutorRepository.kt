package com.lobato.pdm_taller04_grupo10.Repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.lobato.pdm_taller04_grupo10.database.daos.AutorDAO
import com.lobato.pdm_taller04_grupo10.database.entities.Autor

class AutorRepository (private val autorDao: AutorDAO){

    @WorkerThread
    suspend fun insert(repo: Autor){
        autorDao.insertAuthor(repo)
    }

    fun getAll(): LiveData<List<Autor>> = autorDao.getAuthors()

    fun getAuthorByName(): LiveData<List<Autor>> = autorDao.getAuthorByName("autor")

    fun delete()= autorDao.deleteAuthor()
}