package com.lobato.pdm_taller04_grupo10.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lobato.pdm_taller04_grupo10.R
import com.lobato.pdm_taller04_grupo10.database.entities.Libro
import com.lobato.pdm_taller04_grupo10.database.entities.Tags
import com.lobato.pdm_taller04_grupo10.database.entities.Autor

class BookAdapter internal constructor(context: Context): RecyclerView.Adapter<BookAdapter.BookViewHolder>(){

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var books = emptyList<Libro>()
    private var authors = emptyList<Autor>()
    private var tags = emptyList<Tags>()

    inner class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val CurrentBookEdition : TextView = itemView.findViewById(R.id.edicion)
        val CurrentBookTitle : TextView = itemView.findViewById(R.id.titulo)
        val Resume : TextView = itemView.findViewById(R.id.resumen)
        val CurrentEditorial : TextView = itemView.findViewById(R.id.imprenta)
        val ISBN : TextView = itemView.findViewById(R.id.isbn)
        val Authors : TextView = itemView.findViewById(R.id.autores)
        val Tags : TextView = itemView.findViewById(R.id.tags)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val itemView = inflater.inflate(R.layout.book_view_land, parent, false)
        return BookViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val current = books[position]
        val actual = authors[position]
        val puntero = tags[position]
        holder.CurrentBookTitle.text = current.title
        holder.CurrentBookEdition.text.toString().toInt() = current.edition
        holder.Resume.text = current.description
        holder.CurrentEditorial.text.toString().toLong() = current.editorial
        holder.ISBN.text = current.isbn
        holder.Authors.text = actual.name
        holder.Tags.text = puntero.name
    }

    internal fun setBooks(books: List<Libro>, authors: List<Autor>, tags:List<Tags>) {
        this.books = books
        this.authors = authors
        this.tags = tags
        notifyDataSetChanged()
    }

    override fun getItemCount() = books.size
}