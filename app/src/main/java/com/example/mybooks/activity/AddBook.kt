

package com.example.mybooks.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.room.*
import com.example.mybooks.database.AppDatabase
import com.example.mybooks.R
import com.example.mybooks.model.Book
import kotlinx.android.synthetic.main.activity_add_book.*

class AddBook : AppCompatActivity() {

    val db: AppDatabase by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java, "bookdb.sqlite")
            .allowMainThreadQueries()
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_book)

        buttonSalvar.setOnClickListener {
            db.bookDao().inserir(
                Book(
                    textTitle.text.toString(),
                    textAutor.text.toString(),
                    textAno.text.toString().toInt(),
                    ratingBarBota.rating
                )
            )
            Log.i("Add","Adicionou um livro")
            db.bookDao().listAll().forEach { Log.i("APPBOOK", it.toString()) }
        }

        btnList.setOnClickListener {
            finish()
        }

    }



}