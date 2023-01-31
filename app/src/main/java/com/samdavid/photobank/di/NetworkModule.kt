package com.samdavid.photobank.di

import com.google.gson.Gson
import com.samdavid.photobank.constants.API_KEY
import com.samdavid.photobank.constants.BASE_URL
import com.samdavid.photobank.data.PixaBayApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    private val TIMEOUT = 10L
    @Singleton
    @Provides
    @Named("logging")
    fun provideLoggingInteceptor(): Interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    @Named("header")
    fun provideHeaderInterceptor(): Interceptor = Interceptor { chain ->
        val request = chain.request()
        val newUrl = request.url.newBuilder()
            .addQueryParameter("api_key", API_KEY)
            .build()
        val newRequest = request.newBuilder()
            .url(newUrl)
            .method(request.method, request.body)
            .build()
        chain.proceed(newRequest)
    }

    @Singleton
    @Provides
   fun provideOkHttpClient(
       @Named("logging") loggingInterceptor: Interceptor,
       @Named("header") headerInterceptor: Interceptor
   ): OkHttpClient = OkHttpClient.Builder()
       .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
       .readTimeout(TIMEOUT, TimeUnit.SECONDS)
       .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
       .apply {
           addInterceptor(loggingInterceptor)
           addInterceptor(headerInterceptor)
       }
       .build()

    @Singleton
    @Provides
    fun provideRetrofitClient(okHttpClient: OkHttpClient, gson: Gson): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): PixaBayApiService = retrofit.create(PixaBayApiService::class.java)
}