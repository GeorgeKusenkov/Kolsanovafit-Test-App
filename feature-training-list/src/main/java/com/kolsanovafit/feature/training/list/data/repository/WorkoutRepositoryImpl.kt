package com.kolsanovafit.feature.training.list.data.repository

import com.kolsanovafit.feature.training.list.data.api.RetrofitBuilder
import com.kolsanovafit.feature.training.list.data.model.toDomain
import com.kolsanovafit.feature.training.list.domain.model.Workout
import com.kolsanovafit.feature.training.list.domain.repository.WorkoutRepository

class WorkoutRepositoryImpl(): WorkoutRepository {
    private val api = RetrofitBuilder.workoutApi

    override suspend fun getWorkouts(): List<Workout> {
        val dtoTrainingList = api.getTrainings()
        return dtoTrainingList.map {
            it.toDomain()
        }
    }

}