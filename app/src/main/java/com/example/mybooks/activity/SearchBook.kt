package com.example.mybooks.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.room.Room
import com.example.mybooks.R
import com.example.mybooks.database.AppDatabase
import com.example.mybooks.model.Book
import kotlinx.android.synthetic.main.activity_list_books.*

class SearchBook : AppCompatActivity() {

    val db: AppDatabase by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java, "bookdb.sqlite")
            .allowMainThreadQueries()
            .build()
    }

    var books = ArrayList<Book>()

    private val TITLES = arrayListOf<String>()

    var indice = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_book)

        fillBooks()
        fillAdapter()

        var booksToListAdapter = ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, TITLES)
        titleTextAdapter.setAdapter(booksToListAdapter)
        titleTextAdapter.setOnItemClickListener { adapterView, view, i, l ->
            var selected = adapterView.getItemAtPosition(i)
            var indiceCurrent = db.bookDao().findByName("${selected.toString()}").id
            indice = indiceCurrent-1
            getBook()
            Log.i("Teste do selecred","${selected.toString()}")
            //Toast.makeText(this, "$selected $l", Toast.LENGTH_SHORT).show()
        }
    }


    fun fillBooks(){
        db.bookDao().listAll().forEach { books.add(it) }
    }

    fun fillAdapter(){

        for(book in books){
            TITLES.add(book.title)
        }

    }

    fun getBook(){

        if(books.size > 0) {

            titleid.text = books.get(indice).title
            autorid.text = books.get(indice).autor
            anoid.text = books.get(indice).ano.toString()
            notaid.text = books.get(indice).score.toString()
        }
    }
}
