package com.kolsanovafit.feature.training.details.data.api

import com.kolsanovafit.feature.training.details.data.model.WorkoutVideoDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface WorkoutVideoApi {
    @GET("get_video")
    suspend fun getVideoById(
        @Query("id") id: Int
    ): WorkoutVideoDTO
}

