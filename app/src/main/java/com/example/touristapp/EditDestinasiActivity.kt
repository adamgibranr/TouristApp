package com.example.touristapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class EditDestinasiActivity : AppCompatActivity() {

    private lateinit var position: String

    private lateinit var input_nama: TextInputEditText
    private lateinit var input_kategori: TextInputEditText
    private lateinit var input_lokasi: TextInputEditText
    private lateinit var input_titik: TextInputEditText
    private lateinit var input_gambar: ImageView

    private lateinit var btn_exit: ImageButton
    private lateinit var btn_ok: ImageButton

    private lateinit var dbRef: DatabaseReference
    private lateinit var storageRef: StorageReference
    private lateinit var email: String
    private lateinit var uri: Uri

    private var latLng: LatLng? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_destinasi)

        position = intent.getStringExtra("position")!!

        val user = Firebase.auth.currentUser
        user?.let {
            // Name, email address, and profile photo Url
            email = it.email.toString()
        }

        dbRef = FirebaseDatabase.getInstance().getReference("Destinasi")
        storageRef = FirebaseStorage.getInstance().getReference("FotoDestinasi")

        input_nama = findViewById(R.id.input_edit_nama)
        input_kategori = findViewById(R.id.input_edit_kategori)
        input_lokasi = findViewById(R.id.input_edit_lokasi)
        input_titik = findViewById(R.id.input_edit_titik)
        input_gambar = findViewById(R.id.input_edit_gambar)

        btn_exit = findViewById(R.id.btn_exit_dtldestinasi)
        btn_ok = findViewById(R.id.btn_ok_dtldestinasi)

        val galleryImage = registerForActivityResult(
            ActivityResultContracts.GetContent(),
            ActivityResultCallback {
                input_gambar.setImageURI(it)
                uri = it
            }
        )

        input_gambar.setOnClickListener {
            galleryImage.launch("image/*")
        }

        btn_ok.setOnClickListener {
            saveDestinasiData()
        }

        btn_exit.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun saveDestinasiData() {
        var nama = input_nama.text.toString()
        var kategori = input_kategori.text.toString()
        var lokasi = input_lokasi.text.toString()
        var pemilik = email
        var titik = input_titik.text.toString()

        if (nama.isEmpty()){
            input_nama.error = "Masukkan inputan data"
        }
        if (kategori.isEmpty()){
            input_kategori.error = "Masukkan inputan data"
        }
        if (lokasi.isEmpty()){
            input_lokasi.error = "Masukkan inputan data"
        }
        if (titik.isEmpty()){
            input_titik.error = "Masukkan inputan data"
        }

        if (nama.isEmpty() or kategori.isEmpty() or lokasi.isEmpty() or titik.isEmpty()) {
            Toast.makeText(this, "Inputan data kosong", Toast.LENGTH_LONG).show()
        }

        else {

            storageRef.child(System.currentTimeMillis().toString())
                .putFile(uri)
                .addOnSuccessListener { task ->
                    task.metadata!!.reference!!.downloadUrl
                        .addOnSuccessListener { uri ->
                            val urlGambar = uri.toString()

                            val destinasiId = position

                            val destinasi = Destinasi(destinasiId, nama, kategori, lokasi, pemilik, titik, urlGambar, latLng)

                            dbRef.child(destinasiId).setValue(destinasi)
                                .addOnCompleteListener {
                                    Toast.makeText(this, "Succces", Toast.LENGTH_LONG).show()

                                }.addOnFailureListener {
                                    Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
                                }
                        }
                }


        }
    }
}