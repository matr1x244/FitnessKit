package com.example.fitnesskit.domain

import com.example.fitnesskit.domain.data.TrainingModel

interface RepositoryTraining {

    suspend fun listTraining(): TrainingModel
}