package com.kolsanovafit.feature.training.list.domain.model

enum class WorkoutType(val code: Int) {
    TRAINING(1),
    LIVE(2),
    COMPLEX(3),
    UNKNOWN(-1);

    companion object {
        fun fromCode(code: Int?): WorkoutType =
            WorkoutType.entries.firstOrNull { it.code == code } ?: UNKNOWN
    }
}