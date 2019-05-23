package com.lobato.pdm_taller04_grupo10.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lobato.pdm_taller04_grupo10.database.entities.Editorial
import com.lobato.pdm_taller04_grupo10.database.entities.Libro

interface EditorialDAO {

    //TODO METEDO POR SI HACEMOS LO DE BUSQUEDA
    @Query("SELECT * FROM Editorial WHERE name==:name")
    fun getEditorialByname(name: String): LiveData<List<Editorial>>

    @Query("SELECT *FROM Editorial")
    fun getAllEditorials():LiveData<List<Editorial>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEditorial(editorial: Editorial)

    @Query("DELETE FROM Editorial")
    fun deleteEditorial()
}