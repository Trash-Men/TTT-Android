package com.tjrwns8024.ttt_android.di.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BaseModule {
    @Provides
    @Singleton
    fun provideCompositeDisposable(): CompositeDisposable =
        CompositeDisposable()
}