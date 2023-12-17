package com.example.touristapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class DetailDestinasiActivity : AppCompatActivity() {

    private lateinit var position: String

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






    }
}