package com.example.touristapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast

class OpsiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opsi)

        val opsi = findViewById<ImageView>(R.id.menu_more)
        opsi.setOnClickListener {
            val popupMenu : PopupMenu = PopupMenu(this,opsi)
            popupMenu.menuInflater.inflate(R.menu.menu_popup,popupMenu.menu)
            popupMenu.setOnMenuItemClickListener (PopupMenu.OnMenuItemClickListener {
                item -> when(item.itemId) {
                    R.id.action1->
                        Toast.makeText(this, "Edit", Toast.LENGTH_SHORT).show()

                    R.id.action2->
                    Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show()
                }
                true
            })
            popupMenu.show()
        }
    }
}