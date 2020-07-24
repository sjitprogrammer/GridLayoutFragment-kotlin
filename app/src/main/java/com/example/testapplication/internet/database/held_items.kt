package com.example.testapplication.internet.database

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class held_items(
    val item: Item,
    val version_details: List<VersionDetail>
):Parcelable