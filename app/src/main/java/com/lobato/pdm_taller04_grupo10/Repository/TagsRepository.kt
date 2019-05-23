package com.lobato.pdm_taller04_grupo10.Repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.lobato.pdm_taller04_grupo10.database.daos.TagsDAO
import com.lobato.pdm_taller04_grupo10.database.entities.Tags

class TagsRepository (private val tagsDao: TagsDAO){

    @WorkerThread
    suspend fun insert(repo: Tags){
        tagsDao.insertTags(repo)
    }

    fun getAll(): LiveData<List<Tags>> = tagsDao.getAllsTags()

    fun getTagsbyName(): LiveData<List<Tags>> = tagsDao.getTagsByName("tag")

    fun delete()= tagsDao.deleteTags()
}

