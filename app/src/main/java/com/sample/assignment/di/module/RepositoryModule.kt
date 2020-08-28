package com.sample.assignment.di.module

import android.app.Application
import com.sample.assignment.db.DataDao
import com.sample.assignment.repository.Repository
import com.sample.assignment.repository.impl.ListRepositoryImpl
import com.sample.assignment.retrofit.AllApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

/**
Created by Ajay Arya on 28/08/20
 */
/**
 *      It is Repository Module that holds the PlacesListRepository and PlacesDetailRepository for injection
 */
@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(api: AllApi, context: Application, dao: DataDao): Repository {
        return ListRepositoryImpl(
            api,
            context,
            dao
        )
    }
}