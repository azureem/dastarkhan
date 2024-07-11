package com.example.dastarkhan.di

import com.example.dastarkhan.domain.AppRepo
import com.example.dastarkhan.domain.impl.AppRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface RepoModule {

    @Binds
    fun repoModule(repo: AppRepoImpl): AppRepo
}