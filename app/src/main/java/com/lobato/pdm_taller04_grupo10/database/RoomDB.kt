package com.lobato.pdm_taller04_grupo10.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lobato.pdm_taller04_grupo10.database.daos.*
import com.lobato.pdm_taller04_grupo10.database.entities.*

@Database(entities = [Libro::class,
    Autor::class,
    Editorial::class,
    Tags::class,
    userFavorites::class], version = 1, exportSchema = false)
public abstract class RoomDB : RoomDatabase(){

    abstract fun bookDAO():LibroDAO
    abstract fun editorialDAO():EditorialDAO
    abstract fun authorDAO(): AutorDAO
    abstract fun tagsDAO(): TagsDAO
    abstract fun userFavoriteDAO(): userFavoritesDAO

    companion object {
        @Volatile
        private var INSTANCE: RoomDB? = null

        fun getInstance(AppContext: Context): RoomDB{
            val temp = INSTANCE

            if (temp != null){
                return temp
            }

            synchronized(this){
                val instance = Room.databaseBuilder(AppContext, RoomDB::class.java, "BibliotecaDB")
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}