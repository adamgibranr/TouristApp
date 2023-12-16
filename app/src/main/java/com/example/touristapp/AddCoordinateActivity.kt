package com.example.touristapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng

private lateinit var btn_ok: ImageButton
private lateinit var coordinate: LatLng

class AddCoordinateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_coordinate)

        btn_ok = findViewById(R.id.btn_ok_addcoordinate)

        val mapFragment = supportFragmentManager.findFragmentById(
            R.id.map_fragment
        ) as? SupportMapFragment
        mapFragment?.getMapAsync { googleMap ->

            GoogleMap.OnMapClickListener {latLng ->  

                coordinate = latLng
            }

        }

        btn_ok.setOnClickListener {
            val intent = Intent(this, AddDestinasiActivity::class.java)
            intent.putExtra("LATITUDE", coordinate.latitude)
            intent.putExtra("LONGITUDE", coordinate.longitude)
            startActivity(intent)
        }

    }
}