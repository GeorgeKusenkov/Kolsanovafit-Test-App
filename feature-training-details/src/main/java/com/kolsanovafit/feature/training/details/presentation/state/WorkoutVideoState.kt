package com.kolsanovafit.feature.training.details.presentation.state

import com.kolsanovafit.feature.training.details.domain.model.WorkoutVideo

sealed class WorkoutVideoState {
    object Loading : WorkoutVideoState()
    data class Success(val workouts: WorkoutVideo) : WorkoutVideoState()
    data class Error(val message: String) : WorkoutVideoState()
}