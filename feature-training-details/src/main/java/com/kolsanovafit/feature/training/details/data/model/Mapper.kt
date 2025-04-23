package com.kolsanovafit.feature.training.details.data.model

import com.kolsanovafit.feature.training.details.domain.model.WorkoutVideo
import com.kolsanovafit.feature.training.details.BuildConfig
import java.net.URI

fun WorkoutVideoDTO.toDomain(): WorkoutVideo {
    val duration = duration?.takeIf { it.isNotBlank() } ?: "неизвестно"
    val link = link?.resolveAgainst(BuildConfig.API_BASE_URL) ?: ""

    return WorkoutVideo(
        id = id ?: -1,
        duration = duration.trim(),
        link = link
    )
}

fun String.resolveAgainst(base: String): String {
    val baseUri = URI(base)
    return baseUri.resolve(this).toString()
}