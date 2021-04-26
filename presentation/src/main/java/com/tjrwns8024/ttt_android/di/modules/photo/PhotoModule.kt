package com.tjrwns8024.ttt_android.di.modules.photo

import com.tjrwns8024.ttt_android.viewmodel.factory.PhotoViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class PhotoModule {

    @Provides
    fun providePhotoViewModelFactory(
    ): PhotoViewModelFactory = PhotoViewModelFactory()

}