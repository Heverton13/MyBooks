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

class BookAdapter(var c: Context, var books:List<Book>) : BaseAdapter() {

    /*
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var v = LayoutInflater.from(c).inflate(R.layout.book_inflater, parent, false)
        var titleBook = v.findViewById<TextView>(R.id.titleBook)

        var bookAtual = books.get(position)

        titleBook.text = bookAtual.title

        return v
    }

     */


    override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {

        Log.i("HOLDER", "GetView invocado...")

        var holder:BookViewHolder
        var view:View

        if (convertView == null){
            Log.i("HOLDER", "Inflando View...")
            view = LayoutInflater.from(c).inflate(R.layout.book_inflater, viewGroup, false)
            holder = BookViewHolder(view)
            view.tag = holder
        }else{
            view = convertView
            holder  = convertView.tag as BookViewHolder
        }

        val livroescolhido = books[position]
        holder.textViewTitle.text = livroescolhido.title

        /*
        holder.textViewAutor.text = livroescolhido.autor
        holder.textViewQuantidade.text = "${livroescolhido.quantidade}"

        if (livroescolhido.lido) {
            holder.img.setImageResource(R.drawable.open)
        } else {
            holder.img.setImageResource(R.drawable.flat)
        }
         */

        return view
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