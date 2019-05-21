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
    Tags::class], version = 1, exportSchema = false)
public abstract class BookRoomDataBase : RoomDatabase(){

    abstract fun bookDAO():LibroDAO

    abstract fun editorialDAO():EditorialDAO
    abstract fun authorDAO(): AutorDAO
    abstract fun tagsDAO(): TagsDAO

    companion object {
        @Volatile
        private var INSTANCE: BookRoomDataBase? = null

        fun getInstance(AppContext: Context): BookRoomDataBase{
            val temp = INSTANCE

            if (temp != null){
                return temp
            }

            synchronized(this){
                val instance = Room.databaseBuilder(AppContext, BookRoomDataBase::class.java, "BibliotecaDB")
                    .build()

                INSTANCE = instance
                return instance
            }
        }

        private class BookDatabaseCallback(
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

        suspend fun populateDatabase(libroDAO: LibroDAO,autorDAO: AutorDAO,editorialDAO: EditorialDAO) {

            editorialDAO.deleteEditorial()
            autorDAO.deleteAuthor()
            libroDAO.deleteBooks()

            var autor = Autor("Gabriel Garcia Marquéz","Colombia")
            autorDAO.insertAuthor(autor)

            autor = Autor("Stephen king","Estados Unidos")
            autorDAO.insertAuthor(autor)

            autor = Autor("Edgar Allan Poe","Estados Unidos")
            autorDAO.insertAuthor(autor)

            autor = Autor("Salvador Salazar Arrué","El Salvador")
            autorDAO.insertAuthor(autor)

            autor = Autor("Umberto Eco","Italia")
            autorDAO.insertAuthor(autor)

            autor = Autor("Paulo Coelho","Brasil")
            autorDAO.insertAuthor(autor)

            autor = Autor("Karl Marx","Londres,Reino Unido")
            autorDAO.insertAuthor(autor)

            autor = Autor("Isabel Allende","Perú")
            autorDAO.insertAuthor(autor)

            autor = Autor("Terry Pratchett","Londres, Reino Unido")
            autorDAO.insertAuthor(autor)

            autor = Autor("J. K. Rowling","Yate, Reino Unido")
            autorDAO.insertAuthor(autor)


            var editorial = Editorial("Pearson PLC","UK","pearson@plc.com")
            editorialDAO.insertEditorial(editorial)

            editorial = Editorial("Grupo Planeta","España","grupoPlaneta@planet.com")
            editorialDAO.insertEditorial(editorial)

            editorial = Editorial("The Woodbridge Company Ltd","Canada","woodridge@ltd.com")
            editorialDAO.insertEditorial(editorial)

            editorial = Editorial("Bertelsmann AG","Alemania","bertelsmann@ag.com")
            editorialDAO.insertEditorial(editorial)

            editorial = Editorial("China South Publishing & Media Group Co., Ltd","China","chinaSP@co.com")
            editorialDAO.insertEditorial(editorial)



            var libro = Libro("","","","","","")
            libroDAO.insertLibro(libro)
            libro = Libro("World!")
            libroDAO.insertLibro(libro)
        }



    }


}