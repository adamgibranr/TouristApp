package com.example.touristapp

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class KategoriActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kategori)

        var edittextKategori = findViewById<EditText>(R.id.edittext_filter)

        val exitButton : ImageView = findViewById<ImageView>(R.id.btn_exit_kategori)
        exitButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val okButton : ImageView = findViewById<ImageView>(R.id.btn_ok_kategori)
        okButton.setOnClickListener {
            val filter = edittextKategori.text.toString()
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("filter", filter)
            startActivity(intent)
        }
    }
}