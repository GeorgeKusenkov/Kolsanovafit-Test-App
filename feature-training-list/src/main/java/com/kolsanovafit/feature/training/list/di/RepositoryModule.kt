package com.kolsanovafit.feature.training.list.di

import com.kolsanovafit.feature.training.list.data.repository.WorkoutRepositoryImpl
import com.kolsanovafit.feature.training.list.domain.repository.WorkoutRepository
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
    abstract fun bindWorkoutRepository(
        impl: WorkoutRepositoryImpl
    ): WorkoutRepository
}