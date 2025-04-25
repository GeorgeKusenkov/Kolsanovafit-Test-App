package com.kolsanovafit.feature.training.details.domain.usecase

import com.kolsanovafit.feature.training.details.domain.repository.WorkoutVideoRepository
import com.kolsanovafit.feature.training.details.domain.model.WorkoutVideo
import javax.inject.Inject

class GetWorkoutVideoUseCase @Inject constructor(
    private val repository: WorkoutVideoRepository
) {
    suspend operator fun invoke(id: Int): WorkoutVideo {
        return repository.getVideoById(id)
    }
}