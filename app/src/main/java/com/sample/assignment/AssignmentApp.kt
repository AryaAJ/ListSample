package com.sample.assignment

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
Created by Ajay Arya on 28/08/20
 */
@HiltAndroidApp
open class AssignmentApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}