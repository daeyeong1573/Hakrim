package com.example.hakrim.retrofit

import com.example.hakrim.dto.mealp.Meal
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Builder {

    var mealApi: MealApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("hub/mealServiceDietInfo")
            .addConverterFactory(GsonConverterFactory.create())
            .client(callOkhttp())
            .build()

        mealApi = retrofit.create(MealApi::class.java)
    }

    private fun callOkhttp():OkHttpClient {
        val builder = OkHttpClient.Builder()
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(interceptor)
        return builder.build()
    }

}