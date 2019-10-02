package com.example.mybooks

import androidx.room.*

@Dao
interface BookDao {
    @Insert
    fun inserir(carro: Book): Long

    @Delete
    fun deletar(carro: Book): Int

    @Update
    fun atualizar(carro: Book): Int

    @Query("SELECT * FROM table_book")
    fun listAll(): Array<Book>

    @Query("SELECT * FROM TABLE_BOOK WHERE id = :id")
    fun findById(id: Long): Book

    @Query("SELECT * FROM TABLE_BOOK WHERE title = :nome")
    fun findByName (nome: String) : Book

}