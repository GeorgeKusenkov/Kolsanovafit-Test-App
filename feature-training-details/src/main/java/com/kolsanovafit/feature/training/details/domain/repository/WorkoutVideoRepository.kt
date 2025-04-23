package com.kolsanovafit.feature.training.details.domain.repository

import com.kolsanovafit.feature.training.details.domain.model.WorkoutVideo

interface WorkoutVideoRepository {
    suspend fun getVideoById(id: Int): WorkoutVideo
}