package com.lobato.pdm_taller04_grupo10.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.lobato.pdm_taller04_grupo10.Repository.GitHubAutorRepository
import com.lobato.pdm_taller04_grupo10.Repository.GitHubEditorialRepository
import com.lobato.pdm_taller04_grupo10.Repository.GitHubLibroRepository
import com.lobato.pdm_taller04_grupo10.Repository.GitHubTagsRepository
import com.lobato.pdm_taller04_grupo10.database.BookRoomDataBase
import com.lobato.pdm_taller04_grupo10.database.entities.Autor
import com.lobato.pdm_taller04_grupo10.database.entities.Editorial

import com.lobato.pdm_taller04_grupo10.database.entities.Libro
import com.lobato.pdm_taller04_grupo10.database.entities.Tags
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookViewModel(app:Application):AndroidViewModel(app){
    //TODO: Agregare una instancia para cada repositorio para mantener su referencia
    private var autorRepository:GitHubAutorRepository?=null
    private var editorialRepository:GitHubEditorialRepository?=null
    private var libroRepository:GitHubLibroRepository?=null
    private var tagRepository:GitHubTagsRepository?=null
    /*//TODO: Variable de tipo LiveData para almacenar el cache de la lista de libros
    var allBooks:LiveData<List<Libro>>?=null*/
    //TODO: Obteniendo referencia al dao de BookRoomDatabase
    init {
        loadAutor()
        loadEditorial()
        loadTag()
        loadLibro()
    }
    //TODO: INSERTS
    fun insertAutor(autor:Autor)=viewModelScope.launch(Dispatchers.IO){
        autorRepository!!.insert(autor)
    }
    fun insertEditorial(editorial: Editorial)=viewModelScope.launch(Dispatchers.IO){
        editorialRepository!!.insert(editorial)
    }
    fun insertLibro(libro: Libro)=viewModelScope.launch(Dispatchers.IO){
        libroRepository!!.insert(libro)
    }
    fun insertTags(tags: Tags)=viewModelScope.launch(Dispatchers.IO){
        //TODO: ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //TODO: VOY A DESCOMENTAR ESTA LINEA CUANDO REPOSITORY TAG ESTE HECHO
        //tagRepository!!.insert(tags)
        //TODO: ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    }
    //TODO: GET-ALL
    fun getAllAutor():LiveData<List<Autor>> = autorRepository!!.getAll()
    fun getAllEditorial():LiveData<List<Editorial>> = editorialRepository!!.getAll()
    fun getAll_libros():LiveData<List<Libro>> = libroRepository!!.getAll()
    //TODO: ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //TODO: VOY A DESCOMENTAR ESTA LINEA CUANDO REPOSITORY TAG ESTE HECHO
    //fun getAllTag():LiveData<List<Tag>> = tagRepository!!.getAll()
    //TODO: ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    private fun loadAutor(){
        val daoAutor = BookRoomDataBase.getInstance(getApplication()).authorDAO()
        autorRepository= GitHubAutorRepository(daoAutor)

    }
    private fun loadEditorial(){
        val daoEditorial=BookRoomDataBase.getInstance(getApplication()).editorialDAO()
        editorialRepository= GitHubEditorialRepository(daoEditorial)
    }
    private fun loadTag(){
        val daoTag = BookRoomDataBase.getInstance(getApplication()).tagsDAO()
        tagRepository=GitHubTagsRepository(daoTag)
    }
    private fun loadLibro(){
        val daoLibro = BookRoomDataBase.getInstance(getApplication()).bookDAO()
        libroRepository=GitHubLibroRepository(daoLibro)
    }
}