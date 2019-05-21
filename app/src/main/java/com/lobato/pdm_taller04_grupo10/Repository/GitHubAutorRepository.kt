package com.lobato.pdm_taller04_grupo10.Repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.lobato.pdm_taller04_grupo10.database.daos.AutorDAO
import com.lobato.pdm_taller04_grupo10.database.entities.Autor

class GitHubAutorRepository (private val autorDao: AutorDAO){

    @WorkerThread
    suspend fun insert(repo: Autor){
        autorDao.insertAuthor(repo)
    }

    fun getAll(): LiveData<List<Autor>> = autorDao.getAuthor()
}