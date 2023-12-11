package com.example.touristapp

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.net.URI

class AddDestinasiActivity : AppCompatActivity() {

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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_destinasi)

        val user = Firebase.auth.currentUser
        user?.let {
            // Name, email address, and profile photo Url
            email = it.email.toString()
        }

        dbRef = FirebaseDatabase.getInstance().getReference("Destinasi")
        storageRef = FirebaseStorage.getInstance().getReference("FotoDestinasi")

        input_nama = findViewById(R.id.input_nama)
        input_kategori = findViewById(R.id.input_kategori)
        input_lokasi = findViewById(R.id.input_lokasi)
        input_titik = findViewById(R.id.input_titik)
        input_gambar = findViewById(R.id.input_gambar)

        btn_exit = findViewById(R.id.btn_exit_adddestinasi)
        btn_ok = findViewById(R.id.btn_ok_adddestinasi)

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

                            val destinasiId = dbRef.push().key!!

                            val destinasi = Destinasi(destinasiId, nama, kategori, lokasi, pemilik, titik, urlGambar)

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