package com.agaperra.professionaldevelopment.di.module

import androidx.viewbinding.BuildConfig
import com.agaperra.professionaldevelopment.data.network.api.ApiService
import com.agaperra.professionaldevelopment.di.DaggerConstants
import com.agaperra.professionaldevelopment.utils.ApiInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {

    @Singleton
    @Provides
    fun provideBaseUrl(): String = DaggerConstants.DICTIONARY_BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = if (BuildConfig.DEBUG) {
        OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
            .addInterceptor(ApiInterceptor)
            .build()
    } else {
        OkHttpClient.Builder().build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(OkHttpClient: OkHttpClient, baseUrl: String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .baseUrl(baseUrl)
        .client(OkHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

}