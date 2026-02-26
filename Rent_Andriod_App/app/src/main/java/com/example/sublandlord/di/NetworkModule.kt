package com.example.sublandlord.di

import com.example.sublandlord.data.remote.RetrofitClient
import com.example.sublandlord.data.remote.api.SubLandlordApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideSubLandlordApi(): SubLandlordApi {
        return RetrofitClient.api
    }
}
