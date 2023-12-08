package com.example.touristapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddDestinasiActivity : AppCompatActivity() {

    private lateinit var input_nama: TextInputEditText
    private lateinit var input_kategori: TextInputEditText
    private lateinit var input_lokasi: TextInputEditText
    private lateinit var input_pemilik: TextInputEditText
    private lateinit var input_gambar: ImageView

    private lateinit var btn_exit: ImageButton
    private lateinit var btn_ok: ImageButton

    private lateinit var dbRef: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_destinasi)

        dbRef = FirebaseDatabase.getInstance().getReference("Destinasi")

        input_nama = findViewById(R.id.input_nama)
        input_kategori = findViewById(R.id.input_kategori)
        input_lokasi = findViewById(R.id.input_lokasi)
        input_pemilik = findViewById(R.id.input_pemilik)
        input_gambar = findViewById(R.id.input_gambar)

        btn_exit = findViewById(R.id.btn_exit_adddestinasi)
        btn_ok = findViewById(R.id.btn_ok_adddestinasi)

        btn_ok.setOnClickListener {
            saveDestinasiData()
        }
    }

    private fun saveDestinasiData() {
        val nama = input_nama.text.toString()
        val kategori = input_kategori.text.toString()
        val lokasi = input_lokasi.text.toString()
        val pemilik = input_pemilik.text.toString()

        if (nama.isEmpty()){
            input_nama.error = "Masukkan inputan data"
        }
        if (nama.isEmpty()){
            input_kategori.error = "Masukkan inputan data"
        }
        if (nama.isEmpty()){
            input_lokasi.error = "Masukkan inputan data"
        }
        if (nama.isEmpty()){
            input_pemilik.error = "Masukkan inputan data"
        }

        val destinasiId = dbRef.push().key!!

        val destinasi = Destinasi(destinasiId, kategori, lokasi, pemilik )

        dbRef.child(destinasiId).setValue(destinasi)
            .addOnCompleteListener{
                Toast.makeText(this, "Succces", Toast.LENGTH_LONG).show()

            }.addOnFailureListener {
                Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
            }


    }
}