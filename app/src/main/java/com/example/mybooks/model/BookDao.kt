package com.example.mybooks.model

import androidx.room.*

@Dao
interface BookDao {
    @Insert
    fun inserir(book: Book): Long

    @Insert
    fun inserirAll(vararg l: Book): LongArray

    @Delete
    fun deletar(book: Book): Int

    @Query("DELETE FROM table_book")
    fun deleteAll(): Int

    @Update
    fun atualizar(book: Book): Int

    @Query("SELECT * FROM table_book")
    fun listAll(): MutableList<Book>

    @Query("SELECT * FROM TABLE_BOOK WHERE id = :id")
    fun findById(id: Long): Book

    @Query("SELECT * FROM TABLE_BOOK WHERE title = :nome")
    fun findByName (nome: String) : Book

}