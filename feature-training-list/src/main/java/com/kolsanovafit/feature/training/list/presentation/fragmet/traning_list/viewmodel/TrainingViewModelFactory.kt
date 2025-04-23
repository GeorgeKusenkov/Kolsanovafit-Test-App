package com.kolsanovafit.feature.training.list.presentation.fragmet.traning_list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kolsanovafit.feature.training.list.domain.usecase.FilterWorkoutsUseCase
import com.kolsanovafit.feature.training.list.domain.usecase.GetWorkoutsUseCase

class TrainingViewModelFactory(
    private val getWorkoutsUseCase: GetWorkoutsUseCase,
    private val filterWorkoutsUseCase: FilterWorkoutsUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WorkoutListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WorkoutListViewModel(getWorkoutsUseCase, filterWorkoutsUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.simpleName}")
    }
}