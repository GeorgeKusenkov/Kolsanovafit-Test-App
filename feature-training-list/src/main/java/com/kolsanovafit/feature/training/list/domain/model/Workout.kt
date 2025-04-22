package com.kolsanovafit.feature.training.list.domain.model

import com.kolsanovafit.feature.training.list.data.model.WorkoutType

data class Workout (
    val id: Int,
    val title: String,
    val description: String,
    val type: WorkoutType,
    val duration: String
)

