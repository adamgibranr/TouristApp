package com.example.touristapp

import android.content.Intent
import android.graphics.drawable.Icon
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.model.LatLng


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toMainActivity: View = findViewById(R.id.button_destinasi)

        toMainActivity.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val toMainActivity2: View = findViewById(R.id.button_peta)

        toMainActivity2.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        val toKategoriFragment: ImageView = findViewById(R.id.icon3)

        toKategoriFragment.setOnClickListener{
            replaceFragment(KategoriFragment())

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

    private fun replaceFragment(KategoriFragment : Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,KategoriFragment)
        fragmentTransaction.commit()
    }
}