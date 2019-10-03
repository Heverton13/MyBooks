package com.example.mybooks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_list_books.*

class ListBooks : AppCompatActivity() {

    var books = ArrayList<Book>()

    var indice = 0

    val db:AppDatabase by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java, "bookdb.sqlite")
            .allowMainThreadQueries()
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_books)

        fillBooks()

        getBook()

        btnNext.setOnClickListener {
            indice++
            getBook()
        }

        btnPrevious.setOnClickListener {
            indice--
            getBook()
        }

    }

    // Est[a função preenche meu array de livros, com os livros que eu tenho cadastrados no banco
    fun fillBooks(){
        db.bookDao().listAll().forEach { books.add(it) }
    }

    // Função retornar livro quando array for maior que zero fazendo a busca pelo indice atual
    fun getBook(){

        if(books.size > 0){

            titleid.text = books.get(indice).title
            autorid.text =  books.get(indice).autor
            anoid.text = books.get(indice).ano.toString()
            notaid.text =  books.get(indice).score.toString()

            btnPrevious.visibility =  View.VISIBLE
            btnNext.visibility = View.VISIBLE

            checkIndex()

        }else{
            btnPrevious.visibility =  View.INVISIBLE
            btnNext.visibility = View.INVISIBLE
        }

    }

    fun checkIndex(){

        if(indice + 1  >= books.size){
            btnNext.visibility = View.INVISIBLE
        }

        else if(indice - 1 < 0){
            btnPrevious.visibility = View.INVISIBLE
        }
    }

}
