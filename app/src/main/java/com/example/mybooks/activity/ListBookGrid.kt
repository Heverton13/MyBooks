package com.example.mybooks.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.example.mybooks.R
import com.example.mybooks.adapter.BookAdapter
import com.example.mybooks.adapter.BookAdapterGrid
import com.example.mybooks.database.AppDatabase
import com.example.mybooks.model.Book
import kotlinx.android.synthetic.main.activity_list_book_grid.*
import kotlinx.android.synthetic.main.activity_tela_de_lista_book_adaptar.*


class ListBookGrid : AppCompatActivity() {


    val db: AppDatabase by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java, "bookdb.sqlite")
            .allowMainThreadQueries()
            .build()
    }

    var listaBooks:List<Book>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_book_grid)

        listaBooks = db.bookDao().listAll()

        gridListBooks.adapter = BookAdapterGrid(this, listaBooks as List<Book>)
        gridListBooks.setOnItemClickListener{adapterView, view, i, l ->
            var bookselect = listaBooks?.get(i)
            Toast.makeText(this, "${bookselect?.title} id=${bookselect?.id}", Toast.LENGTH_SHORT).show()
        }


    }
}
