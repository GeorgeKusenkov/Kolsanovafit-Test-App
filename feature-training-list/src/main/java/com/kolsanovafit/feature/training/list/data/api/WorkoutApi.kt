package com.kolsanovafit.feature.training.list.data.api

import com.kolsanovafit.feature.training.list.data.model.WorkoutDTO
import retrofit2.http.GET

interface WorkoutApi {
    @GET("get_workouts")
    suspend fun getTrainings(): List<WorkoutDTO>
}