package com.example.touristapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng

private lateinit var btn_ok: ImageButton
private lateinit var mMap: GoogleMap
private lateinit var coordinate: LatLng

class AddCoordinateActivity : AppCompatActivity(), OnMapReadyCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_coordinate)

        btn_ok = findViewById(R.id.btn_ok_addcoordinate)

        val mapFragment = supportFragmentManager.findFragmentById(
            R.id.map_fragment
        ) as? SupportMapFragment
        mapFragment?.getMapAsync ( this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setOnMapClickListener {latLng ->
            coordinate = latLng
        }

//        btn_ok.setOnClickListener {
//
//            val intent = Intent(this, AddDestinasiActivity::class.java)
//            intent.putExtra("latlng", coordinate)
//            startActivity(intent)
//        }
    }

    private fun onOkButtonClick() {
        if (coordinate != null) {
            val intent = Intent(this, AddDestinasiActivity::class.java)
            intent.putExtra("latlng", coordinate)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Please select a coordinate on the map", Toast.LENGTH_SHORT).show()
        }
    }

}