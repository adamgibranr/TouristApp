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

        val toKategoriActivity: View = findViewById(R.id.icon3)

        toKategoriActivity.setOnClickListener {
            val intent = Intent(this, KategoriActivity::class.java)
            startActivity(intent)
        }

        val toAddDestinasiActivity: View = findViewById(R.id.icon2)

        toAddDestinasiActivity.setOnClickListener {
            val intent = Intent(this, AddDestinasiActivity::class.java)
            startActivity(intent)
        }


        val rvDestinasi = findViewById<RecyclerView>(R.id.rvDestinasi)
        //rvDestinasi.adapter = DestinasiAdapter(this, )

        //rvDestinasi.setHasFixedSize(true)


        val listDestinasi = listOf(
            Destinasi(nama = "List 1",),
            Destinasi(nama = "List 2",),
            Destinasi(nama = "List 3",)
        )

        val destinasiAdapter = DestinasiAdapter(listDestinasi)

        rvDestinasi.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = destinasiAdapter
        }

    }

    //val toKategoriFragment: ImageView = findViewById(R.id.icon3)

    //toKategoriFragment.setOnClickListener{
    //    replaceFragment(KategoriFragment())

    //}


    //private fun replaceFragment(KategoriFragment : Fragment) {
    //    val fragmentManager = supportFragmentManager
    //    val fragmentTransaction = fragmentManager.beginTransaction()
    //    fragmentTransaction.replace(R.id.frame_layout,KategoriFragment)
     //   fragmentTransaction.commit()
    //}
}