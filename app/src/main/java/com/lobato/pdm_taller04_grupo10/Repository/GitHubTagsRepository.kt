package com.lobato.pdm_taller04_grupo10.Repository

import androidx.annotation.WorkerThread
import com.lobato.pdm_taller04_grupo10.database.daos.TagsDAO
import com.lobato.pdm_taller04_grupo10.database.entities.Editorial
import com.lobato.pdm_taller04_grupo10.database.entities.Tags

class GitHubTagsRepository (private val tagsDao: TagsDAO){

    @WorkerThread
    suspend fun insert(repo: Tags){

    }
}

