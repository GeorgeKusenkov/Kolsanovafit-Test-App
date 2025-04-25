package com.kolsanovafit.feature.training.list.data.repository

import com.kolsanovafit.feature.training.list.data.api.WorkoutApi
import com.kolsanovafit.feature.training.list.data.model.toDomain
import com.kolsanovafit.feature.training.list.domain.model.Workout
import com.kolsanovafit.feature.training.list.domain.repository.WorkoutRepository
import javax.inject.Inject

class WorkoutRepositoryImpl @Inject constructor(
    private val api: WorkoutApi
): WorkoutRepository {
    override suspend fun getWorkouts(): List<Workout> {
        val dtoTrainingList = api.getTrainings()
        return dtoTrainingList.map {
            it.toDomain()
        }
    }
}