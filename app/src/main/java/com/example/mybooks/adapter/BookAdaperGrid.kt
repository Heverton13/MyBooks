package com.example.mybooks.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.mybooks.R
import com.example.mybooks.model.Book

class BookAdapterGrid(var c: Context, var books:List<Book>) : BaseAdapter() {

    var context:Context = c
    var listabooks:List<Book> = books

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var v = LayoutInflater.from(c).inflate(R.layout.book_inflater_grid, parent, false)
        var titleBook = v.findViewById<TextView>(R.id.titleBook)

        var bookAtual = listabooks.get(position)

        titleBook.text = bookAtual.title

        return v
    }

    override fun getItem(position: Int): Any {
        return listabooks[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return listabooks.size
    }
}