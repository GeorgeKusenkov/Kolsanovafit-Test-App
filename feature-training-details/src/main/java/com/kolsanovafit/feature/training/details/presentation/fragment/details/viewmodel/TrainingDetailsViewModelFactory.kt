package com.kolsanovafit.feature.training.details.presentation.fragment.details.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kolsanovafit.feature.training.details.domain.usecase.GetWorkoutVideoUseCase

class TrainingDetailsViewModelFactory(
    private val getWorkoutVideoUseCase: GetWorkoutVideoUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WorkoutDetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WorkoutDetailsViewModel(getWorkoutVideoUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}