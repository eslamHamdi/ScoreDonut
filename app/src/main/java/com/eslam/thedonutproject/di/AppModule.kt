package com.eslam.thedonutproject.di


import com.eslam.thedonutproject.data.remote.ScoreService
import com.eslam.thedonutproject.domain.DataSource
import com.eslam.thedonutproject.repositories.ScoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {



    @Provides
    @Singleton
    fun provideRetrofit():Retrofit
    {
        val client:OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .callTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
        return Retrofit.Builder()
            .baseUrl("https://android-interview.s3.eu-west-2.amazonaws.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideScoreService(retrofit: Retrofit):ScoreService
    {
        return retrofit.create(ScoreService::class.java)
    }



    @Provides
    @Singleton
    fun provideRepository(service:ScoreService):DataSource
    {
        return ScoreRepository(service = service, dispatcher = Dispatchers.IO)
    }





}