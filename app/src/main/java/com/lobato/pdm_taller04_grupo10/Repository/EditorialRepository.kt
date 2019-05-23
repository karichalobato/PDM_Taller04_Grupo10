package com.lobato.pdm_taller04_grupo10.Repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.lobato.pdm_taller04_grupo10.database.daos.EditorialDAO
import com.lobato.pdm_taller04_grupo10.database.entities.Editorial

class EditorialRepository (private val editDao: EditorialDAO){

    @WorkerThread
    suspend fun insert(repo: Editorial){
        editDao.insertEditorial(repo)
    }

    fun getAll(): LiveData<List<Editorial>> = editDao.getAllEditorials()

    fun getEditorialByName(): LiveData<List<Editorial>> = editDao.getEditorialByname("editorial")

    fun delete() = editDao.deleteEditorial()
}