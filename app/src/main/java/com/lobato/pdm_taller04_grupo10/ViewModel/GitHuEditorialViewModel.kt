package com.lobato.pdm_taller04_grupo10.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.lobato.pdm_taller04_grupo10.Repository.GitHubEditorialRepository
import com.lobato.pdm_taller04_grupo10.database.RoomDB
import com.lobato.pdm_taller04_grupo10.database.entities.Editorial
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GitHuEditorialViewModel (app: Application): AndroidViewModel (app){
    private val repository: GitHubEditorialRepository

    init {
        val EditDao = RoomDB.getInstance(app).editorialDAO()
        repository = GitHubEditorialRepository(EditDao)
    }

    fun insert(repo: Editorial) = viewModelScope.launch (Dispatchers.IO) {
        repository.insert(repo)
    }

    fun getAll(): LiveData<List<Editorial>> = repository.getAll()

}