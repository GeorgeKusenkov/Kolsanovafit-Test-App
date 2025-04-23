package com.kolsanovafit.feature.training.details.domain.usecase

import com.kolsanovafit.feature.training.details.domain.repository.WorkoutVideoRepository
import com.kolsanovafit.feature.training.details.domain.model.WorkoutVideo

class GetWorkoutVideoUseCase(private val repository: WorkoutVideoRepository) {
    suspend operator fun invoke(id: Int): WorkoutVideo {
        return repository.getVideoById(id)
    }
}