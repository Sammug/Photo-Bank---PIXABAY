package com.samdavid.photobank.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named
import javax.inject.Qualifier
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object CoroutinesModule {
    //annotations
    @Retention(AnnotationRetention.RUNTIME)
    @Qualifier
    annotation class MainDispatcher
    @Retention(AnnotationRetention.RUNTIME)
    @Qualifier
    annotation class IODispatcher
    @Retention(AnnotationRetention.RUNTIME)
    @Qualifier
    annotation class DefaultDispatcher
    //end annotations
    @Provides
    @Singleton
    @MainDispatcher
    fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @Provides
    @Singleton
    @IODispatcher
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Singleton
    @DefaultDispatcher
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
}