package com.piyush.itunessearch.injection

import com.piyush.itunessearch.data.Repository
import com.piyush.itunessearch.data.RepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepoModule {
    @Binds
    internal abstract fun provideRepository(repository: RepositoryImpl): Repository
}