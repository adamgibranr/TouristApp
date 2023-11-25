package com.example.touristapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.model.LatLng


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
            Destinasi(name = "List 1", image = "", LatLng(-6.200000,106.816666)),
            Destinasi(name = "List 2", image = "",LatLng(-7.161367,113.482498)),
            Destinasi(name = "List 3", image = "",LatLng(5.548290,95.323753))
        )

        val destinasiAdapter = DestinasiAdapter(listDestinasi)

        rvDestinasi.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = destinasiAdapter
        }


    }
}