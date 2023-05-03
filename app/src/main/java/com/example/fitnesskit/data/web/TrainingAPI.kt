package com.example.fitnesskit.data.web

import com.example.fitnesskit.domain.model.TrainingModel
import retrofit2.http.GET

interface TrainingAPI {

    @GET("get_v3/?club_id=2")
    suspend fun listTraining(): TrainingModel
}