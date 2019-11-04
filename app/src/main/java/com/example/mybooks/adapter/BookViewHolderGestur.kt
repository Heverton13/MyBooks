package com.example.mybooks.adapter

import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mybooks.R

class BookViewHolderGestur(v: View) : RecyclerView.ViewHolder(v) {

    val title:TextView = v.findViewById(R.id.nameTitle)
    val layoutNormal: LinearLayout = v.findViewById(R.id.layout_normal)
    val layoutGone: LinearLayout = v.findViewById(R.id.layout_gone)
    val undoButton: Button = v.findViewById(R.id.undo_button)



}