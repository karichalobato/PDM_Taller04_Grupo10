package com.lobato.pdm_taller04_grupo10.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lobato.pdm_taller04_grupo10.database.entities.userFavorites

@Dao
interface userFavoritesDAO {

    @Query("SELECT * FROM UserFavorites")
    fun getAllFB():LiveData<List<userFavorites>>

    @Query("DELETE FROM UserFavorites")
    fun deleteFB()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFB(userFavorites: userFavorites)


}