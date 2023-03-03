package com.project.hub.application.di

import com.project.hub.application.Configs.serverUrl
import com.project.hub.core.data.network.adapter.ResultAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

//    const val API_URL: String = "https://project-hub.herokuapp.com/api/"

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return createRetrofit(serverUrl, okHttpClient).build()
    }

    @Provides
    @Singleton
    @Named("authRetrofit")
    fun provideAuthRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val tempUrl = "https://web-store-mern.onrender.com/"
        return createRetrofit(tempUrl, okHttpClient).build()
    }

    @Provides
    @Singleton
    fun provideProjectApi(retrofit: Retrofit): Nothing = TODO()

    @Provides
    @Singleton
    fun provideProfileApi(retrofit: Retrofit): Nothing = TODO()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(createLoggingInterceptor())
            .build()
    }

    private fun createRetrofit(url: String, okHttpClient: OkHttpClient) = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addCallAdapterFactory(ResultAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())

    private fun createLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }

}