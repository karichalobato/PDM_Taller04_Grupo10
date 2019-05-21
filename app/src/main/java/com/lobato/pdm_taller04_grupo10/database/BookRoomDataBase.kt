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
                        populateDatabase(database.bookDAO(),database.authorDAO(),database.editorialDAO())
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



            var libro = Libro("https://cdn.shopify.com/s/files/1/1882/8385/products/9780525562443_800x.jpg?v=1522387277",
                "Cien años de soledad",5,
                "La novela Cien años de soledad fue escrita por Gabriel García Márquez durante dieciocho meses,entre 1965 y 1966 en la Ciudad de México, " +
                        "y se publicó por primera vez a mediados de 1967 en Buenos Aires. La idea original de esta obra surge en 1952 durante un viaje que realiza el autor a su pueblo natal, Aracataca, en compañía de su madre. " +
                        "En su primer libro, La Hojarasca, hace referencia por primera vez a Macondo, y varios de los personajes de esta obra aparecen en algunos de sus cuentos y novelas anteriores.",
                editorial.idEditorial,false,"978-3-16-148410-0")
            libroDAO.insertLibro(libro)

            libro = Libro("https://imagessl6.casadellibro.com/a/l/t5/56/9788478886456.jpg",
                "Harry Potter y el Cáliz de Fuego",
                1,"A lo largo de las tres novelas anteriores pertenecientes a la saga de Harry Potter, el protagonista, Harry Potter lucha con las dificultades acarreadas por su adolescencia y por el hecho de ser un mago famoso. " +
                        "Cuando Harry era un niño pequeño, Voldemort, el mago tenebroso más poderoso de la historia, había asesinado a los padres de Harry, pero luego se había desvanecido misteriosamente después de que su maldición asesina en contra de Harry rebotase. " +
                        "El hecho había causado que Harry adquiriera fama inmediata y que fuese colocado bajo los cuidados de sus tíos muggles, Petunia y Vernon, quienes a su vez tenían un hijo llamado Dudley Dursley.",
                editorial.idEditorial,false,"978-3-16-148434-0")
            libroDAO.insertLibro(libro)

            libro = Libro("data:image/jpeg;base64,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",
                "Harry Potter y el Misterio del Principe Mestizo",
                1,"La novela relata los acontecimientos posteriores a Harry Potter y la Orden del Fénix y que preceden a Harry Potter y las Reliquias de la Muerte. Desarrollada en el sexto año de Harry Potter en el Colegio Hogwarts, la obra explora el pasado del mago oscuro Lord Voldemort, así como los preparativos del protagonista junto con su mentor Albus Dumbledore para la batalla final detallada en el siguiente libro, el último de la serie.",
                editorial.idEditorial,false,"978-3-16-148410-1")
            libroDAO.insertLibro(libro)

            libro = Libro("https://www.planetadelibros.com/usuaris/libros/fotos/9/m_libros/9788408074762.jpg",
                "Once Minutos",2,"Once minutos es un libro del escritor brasileño Paulo Coelho, publicado en el año 2003. La novela relata la vida de Maria, una joven de una aldea remota de Brasil. Había una vez una prostituta llamada María...Como un cuento de hadas contado para adultos, así comienza la novela que conmovió al mundo."
                ,editorial.idEditorial,false,"700-3-16-148410-56")
            libroDAO.insertLibro(libro)

            libro = Libro("https://cdn.gandhi.com.mx/media/catalog/product/cache/1/image/370x/9df78eab33525d08d6e5fb8d27136e95/9/7/9786073130806.jpg",
                "El amante japonés",3,"La historia gira en torno a Alma, una anciana muy reservada y que vive en una casa de reposo llamada Lark House. Alma tiene ochenta y un años y lleva una vida misteriosa de la cual poco se sabe hasta que Irina, una muchacha que trabaja en la residencia comienza a espiarla de una buena manera, ya que se siente intrigada con la vida de Alma.",
                editorial.idEditorial,false,"978-3-40-148410-2")
            libroDAO.insertLibro(libro)

            libro = Libro("https://imagessl0.casadellibro.com/a/l/t5/60/9788401019760.jpg",
                "Más allá del invierno",4,
                "Más allá del invierno es una novela que combina elementos autobiográficos y fantásticos de la autora chilena, Isabel Allende. Publicada en 2017, tiene como escenario la ciudad de Nueva York, más precisamente el barrio de Brooklyn donde los tres personajes principales: Lucía Maraz , Richard Bowmaster y Evelyn Ortega vivirán una historia que trascenderá el frío invierno que les tocará compartir. El inicio de esta historia está signado por las palabras que el escritor francés Albert Camus en su obra de 1952 postula: " +
                        "En el medio del invierno aprendí por fin que había en mi un verano invencible.",
                editorial.idEditorial,false,"978-2-16-148510-0")
            libroDAO.insertLibro(libro)

            libro = Libro("https://images-na.ssl-images-amazon.com/images/I/51lu3EW83XL.jpg",
                "La casa de los espíritus",1,
                "La casa de los espíritus, primera novela de Isabel Allende, narra las vivencias de cuatro generaciones de una familia, y la forma en la que ésta se ve afectada por las transformaciones que vive Chile, el país donde se desarrolla la obra.\n" +
                        "\n" + "La historia se inicia con una remembranza del diario de la niña Clara Del Valle en un Jueves Santo, seguido por un comentario en una misa de mediodía en la capital de un país desconocido de Sudamérica. El escandaloso incidente en la iglesia establece el tono del resto de la novela. " +
                        "Clara seguiría escribiendo en su diario (al que ella se refería como cuadernos de escribir la vida) hasta su muerte.",
                editorial.idEditorial,false,"978-3-16-148410-90")
            libroDAO.insertLibro(libro)

            libro = Libro("https://images-eu.ssl-images-amazon.com/images/I/51mO6xuCrnL.jpg",
                "Animales Fantasticos y Donde Encontrarlos",1,
                "Animales fantásticos y dónde encontrarlos contiene la historia de la magizoología y describe las 75 especies mágicas encontradas alrededor del mundo. Scamander dice que recolectó la mayoría de la información encontrada en el libro a través de observaciones hechas en años de viajes por los cinco continentes. Él nota que la primera edición fue encargada en 1918 por el señor Augustus Worme de Obscurus Books. Sin embargo, no fue publicado hasta 1927. Ahora está en su quincuagésima segunda edición.\n" +
                        "\n",
                editorial.idEditorial,false,"078-3-11-148410-0")
            libroDAO.insertLibro(libro)

            libro = Libro("https://imagessl1.casadellibro.com/a/l/t5/81/9788498389081.jpg",
                "Animales fantásticos: Los crímenes de Grindelwald ",1,
                "Al final de Animales fantásticos y dónde encontrarlos, el poderoso mago tenebroso Gellert Grindelwald es capturado en Nueva York con la ayuda de Newt Scamander. Pero, cumpliendo su amenaza, Grindelwald se escapa y se dispone a juntar seguidores, que no sospechan de sus motivos reales: criar magos de sangre limpia para gobernar a todos los seres no mágicos.",
                editorial.idEditorial,false,"988-3-16-18410-4")
            libroDAO.insertLibro(libro)

            libro = Libro("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ4apzJGp5WoiQgsRMm9HZY-JiITJ32kIYHHC0h6JkWwBkUb4oc",
                "Harry Potter y la piedra filosofal ",1,
                "Harry vive con sus horribles tíos y el insoportable primo Dudley, hasta que su ingreso en el Colegio Hogwarts de Magia y Hechicería cambia su vida para siempre. Allí aprenderá trucos y encantamientos fabulosos, y hará un puñado de buenos amigos... aunque también algunos temibles enemigos. Y, sobre todo, conocerá los secretos que lo ayudarán a cumplir con su destino.",
                editorial.idEditorial,false,"978-3-16-148910-9")
            libroDAO.insertLibro(libro)

        }



    }


}