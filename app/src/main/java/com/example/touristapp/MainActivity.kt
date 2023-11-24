package com.example.touristapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toMainActivity: TextView = findViewById(R.id.button_destinasi)

        toMainActivity.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val toMainActivity2: TextView = findViewById(R.id.button_peta)

        toMainActivity2.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }


        val rvDestinasi = findViewById<RecyclerView>(R.id.rvDestinasi)
        //rvDestinasi.adapter = DestinasiAdapter(this, )

        //rvDestinasi.setHasFixedSize(true)


        val listDestinasi = listOf(
            Destinasi(name = "List 1", image = ""),
            Destinasi(name = "List 2", image = ""),
            Destinasi(name = "List 3", image = "")
        )

        val destinasiAdapter = DestinasiAdapter(listDestinasi)

        rvDestinasi.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = destinasiAdapter
        }


    }
}