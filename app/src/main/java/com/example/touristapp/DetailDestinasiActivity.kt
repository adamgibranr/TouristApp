package com.example.touristapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

class DetailDestinasiActivity : AppCompatActivity() {

    private lateinit var position: String

    private lateinit var tv_nama: TextView
    private lateinit var tv_kategori: TextView
    private lateinit var tv_lokasi: TextView
    private lateinit var tv_pemilik: TextView
    private lateinit var urlGambar: String
    private lateinit var iv_foto: ImageView
    private lateinit var tv_deskripsi: TextView

    private lateinit var dbRef: DatabaseReference
    private lateinit var storageRef: StorageReference

    private lateinit var btn_exit: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_destinasi)

        btn_exit = findViewById(R.id.btn_exit_dtldestinasi)
        btn_exit.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        position = intent.getStringExtra("position")!!

        dbRef = FirebaseDatabase.getInstance().getReference("Destinasi").child(position)
        storageRef = FirebaseStorage.getInstance().getReference("FotoDestinasi")

        tv_nama = findViewById(R.id.tv_nama_dtldestinasi)
        tv_kategori = findViewById(R.id.tv_kategori_dtldestinasi)
        tv_lokasi = findViewById(R.id.tv_lokasi_dtldestinasi)
        tv_pemilik = findViewById(R.id.tv_pemilik_dtldestinasi)
        iv_foto = findViewById(R.id.imageview_dtlactivity)
        tv_deskripsi = findViewById(R.id.tv_deskripsi_dtldestinasi)

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val nama = snapshot.child("nama").getValue(String::class.java)
                val kategori = snapshot.child("kategori").getValue(String::class.java)
                val lokasi = snapshot.child("lokasi").getValue(String::class.java)
                val pemilik = snapshot.child("pemilik").getValue(String::class.java)
                val deskripsi = snapshot.child("deskripsi").getValue(String::class.java)

                tv_nama.text = nama
                tv_kategori.text = kategori
                tv_lokasi.text = lokasi
                tv_pemilik.text = pemilik
                urlGambar = snapshot.child("urlGambar").getValue(String::class.java)!!
                tv_deskripsi.text = deskripsi

                Picasso.get().load(urlGambar).into(iv_foto)

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })





    }
}