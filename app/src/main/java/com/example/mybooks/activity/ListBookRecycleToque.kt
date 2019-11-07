package com.example.mybooks.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.mybooks.R
import com.example.mybooks.adapter.BookAdapter
import com.example.mybooks.adapter.BookAdapterRecycle
import com.example.mybooks.database.AppDatabase
import com.example.mybooks.gestur.MyRecyclerViewClickListener
import com.example.mybooks.model.Book
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_list_book_recycle_toque.*

class ListBookRecycleToque : AppCompatActivity() {

    val db: AppDatabase by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java, "bookdb.sqlite")
            .allowMainThreadQueries()
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_book_recycle_toque)

        var listaBooks:MutableList<Book> = db.bookDao().listAll()

        var adapter = BookAdapterRecycle(this, listaBooks)
        recyclerToque.adapter = adapter

        val layout = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        recyclerToque.layoutManager = layout

        recyclerToque.addOnItemTouchListener(
            MyRecyclerViewClickListener(
                this@ListBookRecycleToque,
                recyclerToque,
                object : MyRecyclerViewClickListener.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {
                        Toast.makeText(this@ListBookRecycleToque, " ${listaBooks[position].title} :)", Toast.LENGTH_SHORT)
                            .show()
                    }

                    override fun onItemLongClick(view: View, position: Int) {
                        val removida = listaBooks[position]
                        listaBooks.remove(removida)
                        recyclerToque.adapter!!.notifyItemRemoved(position)
                        Toast.makeText(this@ListBookRecycleToque, "Clique longo, Remoção", Toast.LENGTH_SHORT)
                            .show()
                        val snack = Snackbar.make(
                            recyclerToque.parent as View,"Removido", Snackbar.LENGTH_LONG )
                            .setAction("Cancelar") {
                                listaBooks.add(position, removida)
                                recyclerToque.adapter!!.notifyItemInserted(position)
                            }
                        snack.show()
                    }
                })
        )

        recyclerToque.itemAnimator = DefaultItemAnimator()


    }
}
