package com.kolsanovafit.feature.training.details.di

import com.kolsanovafit.feature.training.details.data.repository.WorkoutVideoRepositoryImpl
import com.kolsanovafit.feature.training.details.domain.repository.WorkoutVideoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindWorkoutVideoRepository(
        impl: WorkoutVideoRepositoryImpl
    ): WorkoutVideoRepository
}