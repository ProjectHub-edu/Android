package com.project.hub.application.di

import com.project.hub.core.data.local.SessionManagerImpl
import com.project.hub.core.data.local.sharedpref.SharedPreferences
import com.project.hub.core.domain.SessionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SessionManagerModule {

    @Provides
    @Singleton
    fun provideSessionManager(sharedPreferences: SharedPreferences): SessionManager {
        return SessionManagerImpl(sharedPreferences)
    }


}