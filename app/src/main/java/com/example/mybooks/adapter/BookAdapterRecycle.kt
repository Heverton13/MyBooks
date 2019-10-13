package com.example.mybooks.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import com.example.mybooks.R
import com.example.mybooks.model.Book

class BookAdapterRecycle(var c: Context, var books: List<Book>) : RecyclerView.Adapter<BookViewHolderRecycle>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolderRecycle {
        //Exitem 2 exemplos de layout para ser inflado nessse projeto. Teste os 3.

        //val view = LayoutInflater.from(c).inflate(R.layout.fruta_inflater, parent, false)
        val view = LayoutInflater.from(c).inflate(R.layout.book_card_inflater, parent, false);

        return BookViewHolderRecycle(view)
    }

    override fun getItemCount(): Int {
        return books.size
    }

    override fun onBindViewHolder(holder: BookViewHolderRecycle, position: Int) {

        val frutaescolhida = books[position]
        holder.textViewTitle.text = frutaescolhida.title

        //holder.img.setImageResource(frutaescolhida.img)

        /*
        if (frutaescolhida.bitten) {
            holder.img.setImageResource(R.drawable.bitten)
        } else {
            holder.img.setImageResource(R.drawable.fruit)
        }
        holder.img.setOnClickListener{
            frutaescolhida.bitten = true
            notifyItemChanged(position)
        }

         */
    }

}