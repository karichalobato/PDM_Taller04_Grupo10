package com.lobato.pdm_taller04_grupo10.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lobato.pdm_taller04_grupo10.database.entities.Autor

@Dao
interface AutorDAO {
    @Query("SELECT * FROM Author")
    fun getAuthor():LiveData<List<Autor>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)//HACIENDO UN INSERT Y UN UPDATE EN LA MISMA LINEA
    suspend fun insertAuthor(autor: Autor)//Detener el proceso de entrada hasta que termine. Para poder utilizarlo en corrutinas

}