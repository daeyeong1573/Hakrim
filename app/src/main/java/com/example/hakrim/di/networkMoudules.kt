package com.example.hakrim.di

import com.example.hakrim.BuildConfig
import com.example.hakrim.adpater.RecyclerAdapter
import com.example.hakrim.repository.MainRepository
import com.example.hakrim.retrofit.MealApi
import com.example.hakrim.viewmodel.fragment.MealViewModel
import com.example.hakrim.viewmodel.fragment.SchoolInformationViewModel
import com.example.hakrim.viewmodel.fragment.SchoolScheduleViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

var retrofitModule = module {
    single { okHttp() }
    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(get())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single {
        get<Retrofit>().create(
            MealApi::class.java
        )
    }

}

var viewModelPart = module {
    viewModel { MealViewModel(get()) }
    viewModel { SchoolInformationViewModel(get()) }
    viewModel { SchoolScheduleViewModel(get()) }
}

var repositoryMoudule = module {
    single {
        MainRepository(get())
    }
}
//
//var adapterPart = module {
//    factory {
//        RecyclerAdapter(get())
//    }
//}

private val requestBodyLoggerInterceptor: Interceptor
    get() = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
        else HttpLoggingInterceptor.Level.NONE
    }

private val requestHeaderLoggerInterceptor: Interceptor
    get() = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.HEADERS
        else HttpLoggingInterceptor.Level.NONE
    }


private fun okHttp() = OkHttpClient.Builder()
    .addInterceptor(requestBodyLoggerInterceptor)
    .addInterceptor(requestHeaderLoggerInterceptor)
    .build()


val myModule = listOf(retrofitModule, viewModelPart, repositoryMoudule)
