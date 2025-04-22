package com.kolsanovafit.feature.training.list.presentation.fragmet.traning_list.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kolsanovafit.feature.training.list.domain.usecase.GetWorkoutsUseCase
import com.kolsanovafit.feature.training.list.presentation.state.WorkoutState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WorkoutListViewModel(
    private val getWorkoutsUseCase: GetWorkoutsUseCase
) : ViewModel() {

    private val _trainingList = MutableStateFlow<WorkoutState>(WorkoutState.Loading)
    val trainingList: StateFlow<WorkoutState> = _trainingList.asStateFlow()

    init {
        loadTrainings()
    }

    fun loadTrainings() {
        viewModelScope.launch {
            _trainingList.value = WorkoutState.Loading
            try {
                val list = getWorkoutsUseCase()
                _trainingList.value = WorkoutState.Success(list)
            } catch (e: Exception) {
                Log.d("WOROUTTEST", "Ошибка загрузки тренировок", e)
                _trainingList.value = WorkoutState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
}

