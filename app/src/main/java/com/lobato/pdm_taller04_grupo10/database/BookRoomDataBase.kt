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
                        populateDatabase(database.bookDAO(),database.authorDAO(),database.editorialDAO(),database.tagsDAO())
                    }
                }
            }
        }

        //TODO POBLANDO LA BASE DE DATOS CON DATOS QUEMADOS!!!

        suspend fun populateDatabase(libroDAO: LibroDAO,autorDAO: AutorDAO,editorialDAO: EditorialDAO,tagsDAO: TagsDAO) {

            editorialDAO.deleteEditorial()
            autorDAO.deleteAuthor()
            libroDAO.deleteBooks()
            tagsDAO.deleteTags()

            //TODO+++++++++++++++++++++++++++++++++++++++++++++TODO POBLANDO TABLA TAGS+++++++++++++++++++++++++++++++++++++++++++++
            var tags = Tags("Ciencia")
            tagsDAO.insertTags(tags)

            tags = Tags("Ficción")
            tagsDAO.insertTags(tags)

            tags = Tags("Drama")
            tagsDAO.insertTags(tags)

            tags = Tags("Realismo")
            tagsDAO.insertTags(tags)

            tags = Tags("Mágico")
            tagsDAO.insertTags(tags)

            tags = Tags("Macondo")
            tagsDAO.insertTags(tags)

            tags = Tags("Libro")
            tagsDAO.insertTags(tags)

            tags = Tags("Novela")
            tagsDAO.insertTags(tags)

            tags = Tags("Mestizo")
            tagsDAO.insertTags(tags)

            tags = Tags("Animales")
            tagsDAO.insertTags(tags)

            tags = Tags("Pais")
            tagsDAO.insertTags(tags)

            tags = Tags("Niño")
            tagsDAO.insertTags(tags)

            tags = Tags("Fantasticos")
            tagsDAO.insertTags(tags)

            tags = Tags("Amante")
            tagsDAO.insertTags(tags)

            tags = Tags("Cien")
            tagsDAO.insertTags(tags)

            tags = Tags("Años")
            tagsDAO.insertTags(tags)

            tags = Tags("Espiritus")
            tagsDAO.insertTags(tags)

            tags = Tags("Invierno")
            tagsDAO.insertTags(tags)

            tags = Tags("Harry")
            tagsDAO.insertTags(tags)

            tags = Tags("Soledad")
            tagsDAO.insertTags(tags)

            tags = Tags("Minutos")
            tagsDAO.insertTags(tags)

            //TODO+++++++++++++++++++++++++++++++++++++++++++++TODO POBLANDO TABLA AUTHOR+++++++++++++++++++++++++++++++++++++++++++++


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

            //TODO+++++++++++++++++++++++++++++++++++++++++++++TODO POBLANDO TABLA EDITORIAL+++++++++++++++++++++++++++++++++++++++++++++


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


            //TODO+++++++++++++++++++++++++++++++++++++++++++++TODO POBLANDO TABLA BOOK+++++++++++++++++++++++++++++++++++++++++++++

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



            libro = Libro("https://images-eu.ssl-images-amazon.com/images/I/51Z3jESecpL.jpg",
                "Harry Potter y el Misterio del Principe Mestizo",1,"La novela relata los acontecimientos posteriores a Harry Potter y la Orden del Fénix y que preceden a Harry Potter y las Reliquias de la Muerte. " +
                        "Desarrollada en el sexto año de Harry Potter en el Colegio Hogwarts, la obra explora el pasado del mago oscuro Lord Voldemort, " +
                        "así como los preparativos del protagonista junto con su mentor Albus Dumbledore para la batalla final detallada en el siguiente libro, " +
                        "el último de la serie."
                        ,editorial.idEditorial,false,"978-3-16-148410-1")
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