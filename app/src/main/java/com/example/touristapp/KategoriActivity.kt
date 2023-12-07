package com.example.touristapp

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.model.LatLng

class KategoriActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kategori)

        val exitButton : ImageView = findViewById<ImageView>(R.id.btn_exit_kategori)
        exitButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val okButton : ImageView = findViewById<ImageView>(R.id.btn_ok_kategori)
        okButton.setOnClickListener {

        }

        val rvKategori = findViewById<RecyclerView>(R.id.rvKategori)


        val listKategori = listOf(
            Kategori(name = "Kategori 1"),
            Kategori(name = "Kategori 2"),
            Kategori(name = "Kategori 3")
        )

        val kategoriAdapter = KategoriAdapter(listKategori)

        rvKategori.apply {
            layoutManager = LinearLayoutManager(this@KategoriActivity)
            adapter = kategoriAdapter
        }
    }
}