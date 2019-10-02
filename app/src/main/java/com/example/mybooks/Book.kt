package com.example.mybooks

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "table_book")
data class Book(
    @ColumnInfo(name = "title")
    var title:String,
    @ColumnInfo(name = "autor")
    var autor:String,
    @ColumnInfo(name = "ano")
    var ano:Int,
    @ColumnInfo(name = "score")
    var score:Float){
        @PrimaryKey(autoGenerate = true)
        var id = 0
        @Ignore
        var imagem: Bitmap? = null
}

