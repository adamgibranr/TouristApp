package com.example.touristapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvDestinasi = findViewById<RecyclerView>(R.id.rvDestinasi)
        //rvDestinasi.adapter = DestinasiAdapter(this, )

        //rvDestinasi.setHasFixedSize(true)


        val listDestinasi = listOf(
            Destinasi(name = "Thor", image = ""),
            Destinasi(name = "Captain America", image = ""),
            Destinasi(name = "Iron Man", image = "")
        )

        val destinasiAdapter = DestinasiAdapter(listDestinasi)

        rvDestinasi.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = destinasiAdapter
        }


    }
}