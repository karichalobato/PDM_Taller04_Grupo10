package com.lobato.pdm_taller04_grupo10.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.lobato.pdm_taller04_grupo10.Repository.GitHubLibroRepository
import com.lobato.pdm_taller04_grupo10.database.RoomDB
import com.lobato.pdm_taller04_grupo10.database.entities.Libro
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GitHubLibroViewModel(app: Application): AndroidViewModel(app){
    private val repository: GitHubLibroRepository

    init {
        val LibroDao = RoomDB.getInstance(app).bookDAO()
        repository = GitHubLibroRepository(LibroDao)
    }

    fun insert(repo: Libro)= viewModelScope.launch (Dispatchers.IO){
        repository.insert(repo)
    }

    fun getAll(): LiveData<List<Libro>> = repository.getAll()

    fun deleteAll() = repository.delete()
}