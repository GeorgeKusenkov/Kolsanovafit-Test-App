package com.kolsanovafit.feature.training.list.domain.repository

import com.kolsanovafit.feature.training.list.domain.model.Workout

interface WorkoutRepository {
    suspend fun getWorkouts(): List<Workout>
}