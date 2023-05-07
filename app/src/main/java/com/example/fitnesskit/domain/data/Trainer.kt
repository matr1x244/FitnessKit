package com.example.fitnesskit.domain.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Trainer(
    val description: String,
    val full_name: String,
    val id: String,
    val image_url: String,
    val image_url_medium: String,
    val image_url_small: String,
    val last_name: String,
    val name: String,
    val position: String
): Parcelable