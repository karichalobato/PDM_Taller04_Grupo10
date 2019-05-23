package com.lobato.pdm_taller04_grupo10.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.lobato.pdm_taller04_grupo10.Repository.AutorRepository
import com.lobato.pdm_taller04_grupo10.Repository.EditorialRepository
import com.lobato.pdm_taller04_grupo10.Repository.LibroRepository
import com.lobato.pdm_taller04_grupo10.Repository.TagsRepository
import com.lobato.pdm_taller04_grupo10.database.BookRoomDataBase
import com.lobato.pdm_taller04_grupo10.database.entities.Autor
import com.lobato.pdm_taller04_grupo10.database.entities.Editorial

import com.lobato.pdm_taller04_grupo10.database.entities.Libro
import com.lobato.pdm_taller04_grupo10.database.entities.Tags
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookViewModel(app:Application):AndroidViewModel(app){
    //TODO: Agregare una instancia para cada repositorio para mantener su referencia
    private var autorRepository:AutorRepository?=null
    private var editorialRepository:EditorialRepository?=null
    private var libroRepository:LibroRepository?=null
    private var tagRepository:TagsRepository?=null
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
    fun getAllLibros():LiveData<List<Libro>> = libroRepository!!.getAll()
    fun getAllTags():LiveData<List<Tags>> = tagRepository!!.getAll()

    private fun loadAutor(){
        val daoAutor = BookRoomDataBase.getInstance(getApplication()).authorDAO()
        autorRepository= AutorRepository(daoAutor)

    }
    private fun loadEditorial(){
        val daoEditorial=BookRoomDataBase.getInstance(getApplication()).editorialDAO()
        editorialRepository= EditorialRepository(daoEditorial)
    }
    private fun loadTag(){
        val daoTag = BookRoomDataBase.getInstance(getApplication()).tagsDAO()
        tagRepository=TagsRepository(daoTag)
    }
    private fun loadLibro(){
        val daoLibro = BookRoomDataBase.getInstance(getApplication()).bookDAO()
        libroRepository=LibroRepository(daoLibro)
    }
}