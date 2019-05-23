package com.lobato.pdm_taller04_grupo10.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lobato.pdm_taller04_grupo10.database.entities.Tags

@Dao
interface TagsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)//HACIENDO UN INSERT Y UN UPDATE EN LA MISMA LINEA.
    suspend fun insertTags(tags: Tags)//Detener el proceso de entrada hasta que termine. Para poder utilizarlo en corrutinas

    @Query("SELECT * FROM Tags WHERE name=:name")
    fun getTagsByName(name: String): LiveData<List<Tags>>

    @Query("SELECT * FROM Tags")
    fun getAllsTags():LiveData<List<Tags>>


    @Query("DELETE FROM Tags")
    fun deleteTags()
}