package com.lobato.pdm_taller04_grupo10.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lobato.pdm_taller04_grupo10.R
import com.lobato.pdm_taller04_grupo10.database.entities.Libro
import com.lobato.pdm_taller04_grupo10.database.entities.Tags
import com.lobato.pdm_taller04_grupo10.database.entities.Autor
import com.lobato.pdm_taller04_grupo10.database.entities.Editorial
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.book_view_land.view.*

class BookAdapter internal constructor(context: Context): RecyclerView.Adapter<BookAdapter.BookViewHolder>(){

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var books = emptyList<Libro>()
    private var authors = emptyList<Autor>()
    private var tags = emptyList<Tags>()
    private var editorial = emptyList<Editorial>()


    inner class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindBook(book:Libro)=with(itemView){
            Glide.with(itemView.context)
                .load(book.cover_page)
                .placeholder(R.drawable.ic_launcher_background)
                .into(portada)
            titulo.text = book.title
            edicion.text = book.edition.toString()
            isbn.text = book.isbn
        }
        fun bindAutor(book:Autor)=with(itemView){
            autores.text = book.name
        }
        fun bindEditorial(book:Editorial)=with(itemView){
            imprenta.text = book.name

        }
        fun bindTags(book:Tags)=with(itemView){
            tags.text = book.name

        }
       /* val CurrentBookEdition : TextView = itemView.findViewById(R.id.edicion)
        val CurrentBookTitle : TextView = itemView.findViewById(R.id.titulo)
        val Resume : TextView = itemView.findViewById(R.id.resumen)
        val CurrentEditorial : TextView = itemView.findViewById(R.id.imprenta)
        val ISBN : TextView = itemView.findViewById(R.id.isbn)
        val Authors : TextView = itemView.findViewById(R.id.autores)
        val Tags : TextView = itemView.findViewById(R.id.tags)*/

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val itemView = inflater.inflate(R.layout.book_view_land, parent, false)
        return BookViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bindBook(books[position])
        holder.bindAutor(authors[position])
        holder.bindTags(tags[position])
        holder.bindEditorial(editorial[position])

       /*
        val current = books[position]

        val actual = authors[position]
        val puntero = tags[position]
        val saltin = editorial[position]
        holder.CurrentBookTitle.text = current.title
        holder.CurrentBookEdition.text = saltin.name
        holder.Resume.text = current.description
        holder.CurrentEditorial.text = current.editorial.toString()
        holder.ISBN.text = current.isbn
        holder.Authors.text = actual.name
        holder.Tags.text = puntero.name*/

    }

    internal fun setBooks(books: List<Libro>) {
        this.books = books
        notifyDataSetChanged()
    }

    internal fun setAuthors(authors:List<Autor>){
        this.authors = authors
        notifyDataSetChanged()
    }

    internal fun setTags(tags:List<Tags>){
        this.tags = tags
        notifyDataSetChanged()
    }

    override fun getItemCount() = books.size
}