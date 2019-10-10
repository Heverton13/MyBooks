package com.example.mybooks.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.mybooks.R
import com.example.mybooks.adapter.BookAdapterRecycle
import com.example.mybooks.database.AppDatabase
import kotlinx.android.synthetic.main.activity_list_books_recycle.*

class ListBooksRecycle : AppCompatActivity() {

    val db: AppDatabase by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java, "bookdb.sqlite")
            .allowMainThreadQueries()
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_books_recycle)

        // ver isso https://www.androidpro.com.br/blog/design-layout/recyclerview-cardview/

        var adapter = BookAdapterRecycle(this, db.bookDao().listAll())
        listView2.adapter = adapter

        val layout = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        listView2.layoutManager = layout

    }
}
