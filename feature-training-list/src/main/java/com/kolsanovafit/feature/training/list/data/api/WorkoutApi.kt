package com.kolsanovafit.feature.training.list.data.api

import com.kolsanovafit.feature.training.list.data.model.WorkoutDTO
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface WorkoutApi {
    @GET("get_workouts")
    suspend fun getTrainings(): List<WorkoutDTO>
}

object RetrofitBuilder {

    private const val BASE_URL = "http://ref.test.kolsa.ru/"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()) // ← для JSON
        .client(okHttpClient)
        .build()

    val workoutApi: WorkoutApi = retrofit.create(WorkoutApi::class.java)
}