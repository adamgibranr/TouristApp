package com.example.touristapp

import android.content.Intent
import android.graphics.drawable.Icon
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.database.*


class MainActivity : AppCompatActivity() {

    private lateinit var dbRef: DatabaseReference
    private lateinit var listDestinasi: ArrayList<Destinasi>
    private lateinit var filter: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        filter = intent.getStringExtra("filter")?: "default_filter_value"

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


        val toLoginActivity: View = findViewById(R.id.icon1)

        toLoginActivity.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }


        //val rvDestinasi = findViewById<RecyclerView>(R.id.rvDestinasi)
        //rvDestinasi.adapter = DestinasiAdapter(this, )

        //rvDestinasi.setHasFixedSize(true)


//        val listDestinasi = listOf(
//            Destinasi(nama = "List 1",),
//            Destinasi(nama = "List 2",),
//            Destinasi(nama = "List 3",)
//        )
        listDestinasi = arrayListOf<Destinasi>()

        getDestinasiData()

        //val destinasiAdapter = DestinasiAdapter(listDestinasi)

//        rvDestinasi.apply {
//            layoutManager = LinearLayoutManager(this@MainActivity)
//            adapter = destinasiAdapter
//        }

    }

    private fun getDestinasiData() {
        dbRef = FirebaseDatabase.getInstance().getReference("Destinasi")

        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                listDestinasi.clear()
                if (snapshot.exists()){
                    for (destinasiSnap in snapshot.children){
                        val destinasiData = destinasiSnap.getValue(Destinasi::class.java)
                        listDestinasi.add(destinasiData!!)
                    }
                    //
                    val destinasiAdapter = DestinasiAdapter(listDestinasi,this@MainActivity)

                    val rvDestinasi = findViewById<RecyclerView>(R.id.rvDestinasi)

                    rvDestinasi.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = destinasiAdapter
                        if (filter != "default_filter_value") {
                            destinasiAdapter.filterByCategory(filter)
                        }
                    }
                    //
//                    if (filter != null) {
//                        destinasiAdapter.filterByCategory(filter)
//                    }
//                    else {
//                        destinasiAdapter
//                    }
                }
            }


            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
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