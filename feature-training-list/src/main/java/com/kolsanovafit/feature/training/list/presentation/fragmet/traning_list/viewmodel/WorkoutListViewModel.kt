package com.kolsanovafit.feature.training.list.presentation.fragmet.traning_list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kolsanovafit.feature.training.list.domain.model.Workout
import com.kolsanovafit.feature.training.list.domain.model.WorkoutType
import com.kolsanovafit.feature.training.list.domain.usecase.FilterWorkoutsUseCase
import com.kolsanovafit.feature.training.list.domain.usecase.GetWorkoutsUseCase
import com.kolsanovafit.feature.training.list.presentation.state.WorkoutState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutListViewModel @Inject constructor(
    private val getWorkoutsUseCase: GetWorkoutsUseCase,
    private val filterWorkoutsUseCase: FilterWorkoutsUseCase
) : ViewModel() {

    private val _workoutState = MutableStateFlow<WorkoutState>(WorkoutState.Loading)
    val workoutState: StateFlow<WorkoutState> = _workoutState.asStateFlow()

    private val _currentSearch = MutableStateFlow("")
    private val _currentType = MutableStateFlow<WorkoutType?>(null)

    private val workouts: StateFlow<List<Workout>> = _workoutState
        .map { state ->
            when (state) {
                is WorkoutState.Success -> state.workouts
                else -> emptyList()
            }
        }
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    val filteredWorkouts: StateFlow<List<Workout>> = combine(
        workouts,
        _currentSearch,
        _currentType
    ) { list, query, type ->
        filterWorkoutsUseCase(list, query, type)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    init {
        loadWorkouts()
    }

    fun loadWorkouts() {
        viewModelScope.launch {
            _workoutState.value = WorkoutState.Loading
            try {
                val workouts = getWorkoutsUseCase()
                _workoutState.value = WorkoutState.Success(workouts)
            } catch (e: Exception) {
                _workoutState.value = WorkoutState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }

    fun updateSearch(query: String) {
        _currentSearch.value = query
    }

    fun updateType(type: WorkoutType?) {
        _currentType.value = type
    }
}
