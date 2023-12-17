package com.example.touristapp

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso

class DestinasiAdapter(

    //private val context: Context,
    private val destinasiList: List<Destinasi>,
    private val c: Context

    ) : RecyclerView.Adapter<DestinasiAdapter.DestinasiHolder>() {

    inner class DestinasiHolder(view: View) : RecyclerView.ViewHolder(view) {


        init {
            var mMenus: ImageView = view.findViewById(R.id.menu_more)
            mMenus.setOnClickListener {
                popupMenus(it)
            }
        }


        private fun popupMenus(v: View) {

            val position:String = destinasiList[adapterPosition].destinasiId!!
            val popupMenus = PopupMenu(c, v)
            popupMenus.inflate(R.menu.menu_popup)
            popupMenus.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action1 -> {

                        val intent = Intent(c, EditDestinasiActivity::class.java)
                        intent.putExtra("position", position)
                        c.startActivity(intent)

                        Toast.makeText(c, "Edit", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.action2 -> {
                        deleteRecord(
                            position
                        )
                        notifyDataSetChanged()
                        Toast.makeText(c, "Delete", Toast.LENGTH_SHORT).show()
                        true
                    }
                    else -> true
                }
            }
            popupMenus.show()
            val popup = PopupMenu::class.java.getDeclaredField("mPopup")
            popup.isAccessible = true
            val menu = popup.get(popupMenus)
            menu.javaClass.getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                .invoke(menu, true)
        }

        private val textViewDestinasi: TextView = view.findViewById(R.id.tv_destinasi)
        private val imageDestinasi: ImageView = view.findViewById(R.id.img_destinasi)
        fun bindDestinasi(destinasi: Destinasi) {
            textViewDestinasi.text = destinasi.nama
            Picasso.get().load(destinasi.urlGambar).into(imageDestinasi)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DestinasiHolder {

        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_destinasi, parent, false)

        return DestinasiHolder(adapterLayout)
    }

    override fun getItemCount(): Int = destinasiList.size

    override fun onBindViewHolder(holder: DestinasiHolder, position: Int) {
        //val item = destinasiList[position]
        holder.bindDestinasi(destinasiList[position])
        //holder.textView.text = context.resources.getString(item.stringResourceId)
    }

    fun deleteRecord(id: String) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Destinasi").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(c, "Data dihapus", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(c, "Data gagal dihapus", Toast.LENGTH_SHORT).show()
        }
    }


}