package com.example.mybooks.adapter

import android.content.Context
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mybooks.R
import com.example.mybooks.model.Book
import java.util.*
import java.util.Collections.swap
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class BookAdapterSwap(var c: Context, var books:MutableList<Book>) : RecyclerView.Adapter<BookViewHolderGestur>() {

    private val PENDING_REMOVAL_TIMEOUT:Long = 3000 // 3sec
    var itemsPendingRemoval = ArrayList<Book>()

    private val handler = Handler() // hanlder que vai guardar os runnables que devem ser executados
    var pendingRunnables: HashMap<Book, Runnable> =
        HashMap() // map de frutas com runnables pendentes, para que seja possível cancelar

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolderGestur {

        val view = LayoutInflater.from(c).inflate(R.layout.book_card_gone, parent, false);
        return BookViewHolderGestur(view)

    }

    override fun getItemCount(): Int {
        return books.size
    }

    override fun onBindViewHolder(holder: BookViewHolderGestur, position: Int) {

        val bookcolhido = books[position]
        holder.title.text = bookcolhido.title

        if (itemsPendingRemoval.contains(bookcolhido)) {
            //view do swipe/delete
            holder.layoutNormal.setVisibility(View.GONE)
            holder.layoutGone.setVisibility(View.VISIBLE)
            holder.undoButton.setVisibility(View.VISIBLE)
            holder.undoButton.setOnClickListener {
                // usou o undo, remover a pendingRennable
                val pendingRemovalRunnable = pendingRunnables[bookcolhido]
                Log.i("AULA17", "CLICOU")
                pendingRunnables.remove(bookcolhido)
                if (pendingRemovalRunnable != null) {
                    handler.removeCallbacks(pendingRemovalRunnable)
                }
                itemsPendingRemoval.remove(bookcolhido)
                //binda novamente para redesenhar
                notifyItemChanged(books.indexOf(bookcolhido))
            }
        } else {
            //mostra o padrão
            holder.title.setText(bookcolhido.title)
            holder.layoutNormal.setVisibility(View.VISIBLE)
            holder.layoutGone.setVisibility(View.GONE)
            holder.undoButton.setVisibility(View.GONE)
            holder.undoButton.setOnClickListener(null)

        }

    }

    fun remover (position: Int){
        var book = books[position]

        if (books.contains(book)){
            books.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun removerComTempo(position: Int) {

        val book = books[position]
        if (!itemsPendingRemoval.contains(book)) {
            itemsPendingRemoval.add(book)
            notifyItemChanged(position)
            var pendingRemovalRunnable = Runnable {
                remover(position)
            }
            handler.postDelayed(pendingRemovalRunnable, PENDING_REMOVAL_TIMEOUT)
            pendingRunnables[book] = pendingRemovalRunnable
        }
    }

    fun mover(fromPosition: Int, toPosition: Int) {

        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                swap(books, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                swap(books, i, i - 1)
            }
        }

        notifyItemMoved(fromPosition, toPosition)
        notifyItemChanged(toPosition)
        notifyItemChanged(fromPosition)
    }

}