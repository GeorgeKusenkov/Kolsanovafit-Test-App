package com.kolsanovafit.feature.training.list.domain.usecase

import com.kolsanovafit.feature.training.list.domain.model.Workout
import com.kolsanovafit.feature.training.list.domain.repository.WorkoutRepository

class GetWorkoutsUseCase(private val repository: WorkoutRepository) {
    suspend operator fun invoke(): List<Workout> {
        return repository.getWorkouts()
    }
}