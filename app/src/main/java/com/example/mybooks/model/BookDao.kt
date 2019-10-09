package com.example.mybooks.model

import androidx.room.*

@Dao
interface BookDao {
    @Insert
    fun inserir(book: Book): Long

    @Delete
    fun deletar(book: Book): Int

    @Update
    fun atualizar(book: Book): Int

    @Query("SELECT * FROM table_book")
    fun listAll(): Array<Book>

    @Query("SELECT * FROM TABLE_BOOK WHERE id = :id")
    fun findById(id: Long): Book

    @Query("SELECT * FROM TABLE_BOOK WHERE title = :nome")
    fun findByName (nome: String) : Book

}