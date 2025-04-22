package com.kolsanovafit.feature.training.list.data.model

import com.kolsanovafit.feature.training.list.domain.model.Workout

fun WorkoutDTO.toDomain(): Workout {
    val safeId = id ?: throw IllegalArgumentException("Missing id in TrainingDTO")
    val safeTitle = title?.takeIf { it.isNotBlank() }
        ?: throw IllegalArgumentException("Missing title in TrainingDTO id=$safeId")
    val safeDescription = description.orEmpty()
    val safeDuration = duration?.takeIf { it.isNotBlank() }
        ?: throw IllegalArgumentException("Missing duration in TrainingDTO id=$safeId")

    return Workout(
        id = safeId,
        title = safeTitle,
        description = safeDescription,
        type = WorkoutType.fromCode(type),
        duration = safeDuration
    )
}