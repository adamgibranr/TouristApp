package com.example.touristapp

import android.media.Image
import com.google.android.gms.maps.model.LatLng

data class Destinasi(
    val destinasiId : String? = null,
    val nama: String? = null,
    val kategori: String? = null,
    val lokasi: String? = null,
    val pemilik: String? = null,
    val titik : String? = null,
    val urlGambar: String? = null
    //val latLng: LatLng
)
