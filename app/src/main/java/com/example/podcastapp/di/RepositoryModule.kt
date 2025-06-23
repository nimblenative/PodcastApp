package com.example.podcastapp.di

import com.example.podcastapp.data.repository.PodcastRepositoryImpl
import com.example.podcastapp.domain.repository.PodcastRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPodcastRepository(
        podcastRepositoryImpl: PodcastRepositoryImpl
    ): PodcastRepository
}