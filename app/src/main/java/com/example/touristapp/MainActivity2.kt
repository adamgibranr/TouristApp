package com.example.touristapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.gms.maps.SupportMapFragment
import android.content.Context
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MainActivity2 : AppCompatActivity() {

    private val places: List<Destinasi> = listOf(
        Destinasi(name = "List 1", image = "", LatLng(-6.200000,106.816666)),
        Destinasi(name = "List 2", image = "", LatLng(-7.161367,113.482498)),
        Destinasi(name = "List 3", image = "", LatLng(5.548290,95.323753))
    )

    private fun addMarkers(googleMap: GoogleMap) {
        places.forEach { place ->
            val marker = googleMap.addMarker(
                MarkerOptions()
                    .title(place.name)
                    .position(place.latLng)
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val mapFragment = supportFragmentManager.findFragmentById(
            R.id.map_fragment
        ) as? SupportMapFragment
        mapFragment?.getMapAsync { googleMap ->
            addMarkers(googleMap)
        }

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


    }
}