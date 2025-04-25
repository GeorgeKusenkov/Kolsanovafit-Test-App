package com.kolsanovafit.feature.training.details.presentation.fragment.details.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kolsanovafit.feature.training.details.domain.usecase.GetWorkoutVideoUseCase
import com.kolsanovafit.feature.training.details.presentation.state.WorkoutVideoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutDetailsViewModel @Inject constructor(
    private val getWorkoutVideoUseCase: GetWorkoutVideoUseCase
): ViewModel() {
    private val _workoutVideo = MutableStateFlow<WorkoutVideoState>(WorkoutVideoState.Loading)
    val workoutVideo: StateFlow<WorkoutVideoState> = _workoutVideo.asStateFlow()

    fun getVideo(id: Int) {
        viewModelScope.launch {
            _workoutVideo.value = WorkoutVideoState.Loading
            try {
                val video = getWorkoutVideoUseCase(id)
                _workoutVideo.value = WorkoutVideoState.Success(video)
            } catch (e: Exception) {
                Log.e("DEBUG_VIDEO", "Error loading video id=$id", e)
                _workoutVideo.value = WorkoutVideoState.Error(e.localizedMessage ?: "Unknown error")

            }
        }
    }
}



