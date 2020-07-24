package com.example.testapplication.internet.database

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VersionDetail(
    val rarity: Int,
    val version: VersionX
):Parcelable