package com.example.mybooks.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.room.Room
import androidx.viewpager.widget.PagerAdapter
import com.example.mybooks.R
import com.example.mybooks.database.AppDatabase
import com.example.mybooks.model.Book


class PageAdapterBook(var context: Context, var books:List<Book>)  : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.page_inflater, container, false)

        var titulo = view.findViewById<TextView>(R.id.titulopage)
        titulo.text = books[position].title

        var autor = view.findViewById<TextView>(R.id.autorpage)
        autor.text = books[position].autor

        var ano = view.findViewById<TextView>(R.id.anopage)
        ano.text = books[position].ano.toString()

        var avaliacao = view.findViewById<TextView>(R.id.avaliacaopage)
        avaliacao.text = books[position].score.toString()

        container.addView(view)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    override fun getCount(): Int {
        return books.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return books[position].title
    }

}