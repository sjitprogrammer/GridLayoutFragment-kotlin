package com.example.testapplication.internet.database

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StatX(
    val name: String,
    val url: String
): Parcelable