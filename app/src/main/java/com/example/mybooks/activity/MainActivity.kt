package com.example.mybooks.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mybooks.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnRegister.setOnClickListener {
            var i = Intent(this, AddBook::class.java)
            startActivity(i)
        }

        btnList.setOnClickListener {
            var i = Intent(this, ListBooks::class.java)
            startActivity(i)
        }
    }
}
