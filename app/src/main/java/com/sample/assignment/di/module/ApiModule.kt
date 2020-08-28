package com.sample.assignment.di.module

import com.sample.assignment.retrofit.AllApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit

@Module
@InstallIn(ApplicationComponent::class)
object ApiModule {

    @Provides
    fun providesServiceAPI(retrofit: Retrofit): AllApi {
        return retrofit.create(AllApi::class.java)
    }
}