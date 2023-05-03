package com.example.fitnesskit.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Option(
    val club_name: String,
    val link_android: String,
    val link_ios: String,
    val primary_color: String,
    val secondary_color: String
): Parcelable