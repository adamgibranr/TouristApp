package com.example.touristapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DestinasiAdapter(

    //private val context: Context,
    private val destinasiList: List<Destinasi>

    ) : RecyclerView.Adapter<DestinasiAdapter.DestinasiHolder>() {

    class DestinasiHolder(view: View) : RecyclerView.ViewHolder(view) {

        val textViewDestinasi: TextView = view.findViewById(R.id.tv_destinasi)
        fun bindDestinasi(destinasi: Destinasi){
            textViewDestinasi.text = destinasi.name
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
}