

package com.example.mybooks.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.room.*
import com.example.mybooks.database.AppDatabase
import com.example.mybooks.R
import com.example.mybooks.model.Book
import kotlinx.android.synthetic.main.activity_add_book.*

class AddBook : AppCompatActivity() {

    val db: AppDatabase by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java, "bookdb.sqlite")
            .allowMainThreadQueries()
            .build()
    }

    var flag = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_book)


        buttonSalvar.setOnClickListener {

            if(validar()) {
                db.bookDao().inserir(
                    Book(
                        textTitle.text.toString(),
                        textAutor.text.toString(),
                        textAno.text.toString().toInt(),
                        ratingBarBota.rating
                    )
                )
                clearFields()
                Log.i("Add", "Adicionou um livro")
                db.bookDao().listAll().forEach { Log.i("APPBOOK", it.toString()) }
                Toast.makeText(this, "Livro Salvo!", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Prencha todos os campos!", Toast.LENGTH_SHORT).show()
            }
        }

        btnList.setOnClickListener {
            finish()
        }

    }

    fun clearFields(){
        textTitle.text.clear()
        textAutor.text.clear()
        textAno.text.clear()
        ratingBarBota.rating = 0f
    }


    fun validar(): Boolean {

        var validate = true

        if(textTitle.text.toString().equals("")){
            validate = false
        }

        if(textAutor.text.toString().equals("")){
            validate = false
        }

        if(textAno.text.toString().equals("")){
            validate = false
        }
        if(ratingBarBota.rating < 0f){
            validate = false
        }
        return validate
    }

    fun preparaBanco(){

        db.bookDao().deleteAll()

        val l1 = Book("Harry Potter e a pedra filosofal", "J. K. Rowling", 2000, 4.5f)
        val l2 = Book("Harry Potter e a Câmara Secreta", "J. K. Rowling", 2001, 4.5f)
        val l3 = Book("Harry Potter e o Prisioneiro de Azkaban", "J. K. Rowling", 2002, 4.5f)
        val l4 = Book("Harry Potter e o Cálice de Fogo", "J. K. Rowling", 2003, 4f)
        val l5 = Book("Harry Potter e a Ordem da Fênix", "J. K. Rowling", 2004, 4f)
        val l6 = Book("Harry Potter e o Enigma do Príncipe", "J. K. Rowling", 2005, 5f)
        val l7 = Book("Harry Potter e as Relíquias da Morte", "J. K. Rowling", 2006, 5f)
        val l8 = Book("O pistoleiro", "Stephen King", 1990, 5f)
        val l9 = Book("A Escolha dos Três ", "Stephen King", 1991, 5f)
        val l10 = Book("As Terras Devastadas", "Stephen King", 1992, 5f)
        val l11 = Book("Mago e Vidro", "Stephen King", 1993, 5f)
        val l12 = Book("Lobos de Calla", "Stephen King", 1997, 5f)
        val l13 = Book("Canção de Susannah", "Stephen King", 1999, 5f)

        //cadastra novamente
        db.bookDao().inserirAll(
            l1,
            l2,
            l3,
            l4,
            l5,
            l6,
            l7,
            l8,
            l9,
            l10,
            l11,
            l12,
            l13
        )

        flag = false
    }


}