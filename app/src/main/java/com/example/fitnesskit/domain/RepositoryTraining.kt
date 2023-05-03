package com.example.fitnesskit.domain

import com.example.fitnesskit.domain.model.TrainingModel

interface RepositoryTraining {

    suspend fun listTraining(): TrainingModel
}