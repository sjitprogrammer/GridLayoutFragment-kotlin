package com.example.testapplication.internet.database

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VersionGroupDetail(
    val level_learned_at: Int,
    val move_learn_method: MoveLearnMethod,
    val version_group: VersionGroup
): Parcelable