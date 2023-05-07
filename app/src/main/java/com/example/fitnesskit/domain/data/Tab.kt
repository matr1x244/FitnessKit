package com.example.fitnesskit.domain.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Tab(
    val id: Int,
    val name: String
): Parcelable