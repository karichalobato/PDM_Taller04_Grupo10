package com.lobato.pdm_taller04_grupo10.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lobato.pdm_taller04_grupo10.R
import com.lobato.pdm_taller04_grupo10.adapters.BookAdapter
import com.lobato.pdm_taller04_grupo10.viewmodel.BookViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var bookViewModel: BookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = BookAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        bookViewModel = ViewModelProviders.of(this).get(BookViewModel::class.java)

        bookViewModel.getAllLibros().observe(this, Observer{ books->
            books?.let { adapter.setBooks(it) }
        })
        bookViewModel.getAllAutor().observe(this, Observer{autor->
            autor?.let { adapter.setAuthors(it)}
        })
        bookViewModel.getAllTags().observe(this,Observer{tags->
            tags?.let{adapter.setTags(it)}
        })
    }
}
