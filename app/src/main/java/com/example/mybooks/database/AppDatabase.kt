package com.example.mybooks.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mybooks.model.Book
import com.example.mybooks.model.BookDao


@Database(entities = [Book::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
}
