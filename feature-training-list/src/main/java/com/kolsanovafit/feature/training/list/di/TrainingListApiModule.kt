package com.kolsanovafit.feature.training.list.di

import com.kolsanovafit.feature.training.list.data.api.WorkoutApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TrainingListApiModule {
    @Provides
    @Singleton
    fun provideWorkoutApi(retrofit: Retrofit): WorkoutApi =
        retrofit.create(WorkoutApi::class.java)
}