package com.example.fitnesskit.data.web

import com.example.fitnesskit.domain.RepositoryTraining
import com.example.fitnesskit.domain.data.TrainingModel

class RetrofitRequestImpl(private val api: TrainingAPI) : RepositoryTraining {

    override suspend fun listTraining(): TrainingModel {
        return api.listTraining()
    }
}