package com.example.touristapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class KategoriAdapter (    private val kategoriList: List<Kategori>

) : RecyclerView.Adapter<KategoriAdapter.KategoriHolder>() {

    class KategoriHolder(view: View) : RecyclerView.ViewHolder(view) {

        val textViewKategori: TextView = view.findViewById(R.id.tv_kategori)
        fun bindDestinasi(kategori: Kategori){
            textViewKategori.text = kategori.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KategoriHolder {

        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_kategori, parent, false)

        return KategoriHolder(adapterLayout)
    }

    override fun getItemCount(): Int = kategoriList.size

    override fun onBindViewHolder(holder: KategoriHolder, position: Int) {
        //val item = destinasiList[position]
        holder.bindDestinasi(kategoriList[position])
        //holder.textView.text = context.resources.getString(item.stringResourceId)
    }
}