package com.example.testapplication.internet.database

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class Sprites(
    val back_default: String?=null,
    val back_female: Any?=null,
    val back_shiny: String?=null,
    val back_shiny_female: Any?=null,
    val front_default: String?=null,
    val front_female: Any?=null,
    val front_shiny: String?=null,
    val front_shiny_female: Any?=null
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        TODO("back_female"),
        parcel.readString(),
        TODO("back_shiny_female"),
        parcel.readString(),
        TODO("front_female"),
        parcel.readString(),
        TODO("front_shiny_female")
    ) {
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        TODO("Not yet implemented")
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<Sprites> {
        override fun createFromParcel(parcel: Parcel): Sprites {
            return Sprites(parcel)
        }

        override fun newArray(size: Int): Array<Sprites?> {
            return arrayOfNulls(size)
        }
    }
}