package com.kolsanovafit.feature.training.details.data.repository

import android.util.Log
import com.kolsanovafit.feature.training.details.data.api.WorkoutVideoApi
import com.kolsanovafit.feature.training.details.data.model.toDomain
import com.kolsanovafit.feature.training.details.domain.model.WorkoutVideo
import com.kolsanovafit.feature.training.details.domain.repository.WorkoutVideoRepository
import javax.inject.Inject

class WorkoutVideoRepositoryImpl @Inject constructor(
    private val api: WorkoutVideoApi
): WorkoutVideoRepository {

    override suspend fun getVideoById(id: Int): WorkoutVideo {
        val dtoVideo = api.getVideoById(id)
        Log.d("DEBUG_VIDEO", "DTO from server: $dtoVideo")

        return dtoVideo.toDomain()
    }
}