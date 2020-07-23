package com.example.testapplication.internet.database

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TypeX(
    val slot: Int,
    val type: TypeXX
): Parcelable