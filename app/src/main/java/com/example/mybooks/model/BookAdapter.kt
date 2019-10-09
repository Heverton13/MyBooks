package com.example.mybooks.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.mybooks.R
import java.text.FieldPosition

class BookAdapter(var c: Context, var books:List<Book>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var v = LayoutInflater.from(c).inflate(R.layout.book_inflater, parent, false)
        var titleBook = v.findViewById<TextView>(R.id.titleBook)

        var bookAtual = books.get(position)

        titleBook.text = bookAtual.title

        return v
    }

    override fun getItem(position: Int): Any {
        return books[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return books.size
    }
}