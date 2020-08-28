package com.sample.assignment.di.module

import android.app.Application
import androidx.room.Room
import com.sample.assignment.db.Database
import com.sample.assignment.db.DataDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RoomModule {

    @Singleton
    @Provides
    fun provideDatabase(application: Application): Database {
        return Room.databaseBuilder(application, Database::class.java, "main_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideVenueListDao(database: Database): DataDao {
        return database.venueDao
    }
}