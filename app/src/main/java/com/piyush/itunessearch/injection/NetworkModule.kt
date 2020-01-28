package com.piyush.itunessearch.injection

import com.piyush.itunessearch.data.remote.SearchApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    internal fun provideAttendanceApi(): SearchApi {

        return Retrofit.Builder()
            .baseUrl(SearchApi.ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SearchApi::class.java)

    }
}