package com.kolsanovafit.feature.training.list.presentation.state

import com.kolsanovafit.feature.training.list.domain.model.Workout

sealed class WorkoutState {
    object Loading : WorkoutState()
    data class Success(val workouts: List<Workout>) : WorkoutState()
    data class Error(val message: String) : WorkoutState()
}