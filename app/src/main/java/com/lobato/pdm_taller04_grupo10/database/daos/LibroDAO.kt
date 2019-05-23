package com.lobato.pdm_taller04_grupo10.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lobato.pdm_taller04_grupo10.database.entities.Libro

@Dao
interface LibroDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)//HACIENDO UN INSERT Y UN UPDATE EN LA MISMA LINEA.
    suspend fun insertLibro(libro: Libro)//Detener el proceso de entrada hasta que termine. Para poder utilizarlo en corrutinas
    //TODO POR SI HACEMOS METODO DE BUSQUEDA
    @Query("SELECT * FROM Book WHERE title==:title")
    fun getBookByTitle(title: String): LiveData<List<Libro>>

    @Query("SELECT * FROM Book")
    fun getAllBooks():LiveData<List<Libro>>


    @Query("DELETE FROM Book")
    fun deleteBooks()
}

