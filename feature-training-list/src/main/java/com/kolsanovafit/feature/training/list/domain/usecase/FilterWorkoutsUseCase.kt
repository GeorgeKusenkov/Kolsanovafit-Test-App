package com.kolsanovafit.feature.training.list.domain.usecase

import com.kolsanovafit.feature.training.list.domain.model.Workout
import com.kolsanovafit.feature.training.list.domain.model.WorkoutType
import javax.inject.Inject

class FilterWorkoutsUseCase @Inject constructor(){
    operator fun invoke(
        workouts: List<Workout>,
        searchQuery: String,
        workoutType: WorkoutType?
    ): List<Workout> {
        return workouts
            .filter { workoutType == null || it.type == workoutType }
            .filter { it.title.contains(searchQuery, ignoreCase = true) }
    }
}