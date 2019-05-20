package com.lobato.pdm_taller04_grupo10.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.lobato.pdm_taller04_grupo10.database.daos.*
import com.lobato.pdm_taller04_grupo10.database.entities.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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

        private class WordDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.bookDAO())
                    }
                }
            }
        }

        suspend fun populateDatabase(libroDAO: LibroDAO) {
            libroDAO.deleteBooks()

            var libro = Libro("https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwj23qeYiaviAhUSy1kKHXqbCfwQjRx6BAgBEAU&url=https%3A%2F%2Fwww.casadellibro.com%2Flibro-cien-anos-de-soledad%2F9788420471839%2F1128535&psig=AOvVaw0hIOiwt4BNRlBNrNuNzVO0&ust=1558474796112397",
                "Cien años de soledad",
                Autor)
            libroDAO.insertLibro(libro)
            libro = Libro("World!")
            libroDAO.insertLibro(libro)
        }

    }


}