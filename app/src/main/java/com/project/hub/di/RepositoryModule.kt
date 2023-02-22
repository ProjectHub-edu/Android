package com.project.hub.di

import com.project.hub.data.api.Api
import com.project.hub.data.repository.RepositoryImplementation
import com.project.hub.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideRepository(api: Api): Repository {
        return RepositoryImplementation(api)
    }

}