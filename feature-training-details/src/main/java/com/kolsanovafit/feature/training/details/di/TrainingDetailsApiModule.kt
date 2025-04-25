package com.kolsanovafit.feature.training.details.di

import com.kolsanovafit.feature.training.details.data.api.WorkoutVideoApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TrainingDetailsApiModule {
    @Provides
    @Singleton
    fun provideWorkoutVideoApi(retrofit: Retrofit): WorkoutVideoApi =
        retrofit.create(WorkoutVideoApi::class.java)
}