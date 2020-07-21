package com.example.testapplication


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item(val title: String? = "", val imageUrl: String? = "", val number: Int? = 0) : Parcelable
