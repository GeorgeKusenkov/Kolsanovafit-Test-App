package com.kolsanovafit.feature.training.details.data.api

import com.kolsanovafit.feature.training.details.BuildConfig
import com.kolsanovafit.feature.training.details.data.model.WorkoutVideoDTO
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WorkoutVideoApi {
    @GET("get_video")
    suspend fun getVideoById(
        @Query("id") id: Int
    ): WorkoutVideoDTO
}

object RetrofitBuilder {
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    val workoutApi: WorkoutVideoApi = retrofit.create(WorkoutVideoApi::class.java)
}