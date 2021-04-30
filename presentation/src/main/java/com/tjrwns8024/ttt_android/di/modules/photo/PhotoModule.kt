package com.tjrwns8024.ttt_android.di.modules.photo

import com.tjrwns8024.data.datasource.PhotoDataSource
import com.tjrwns8024.data.datasource.PhotoDataSourceImpl
import com.tjrwns8024.data.datasource.TrashDataSource
import com.tjrwns8024.data.datasource.TrashDataSourceImpl
import com.tjrwns8024.data.remote.Api
import com.tjrwns8024.data.repository.PhotoRepositoryImpl
import com.tjrwns8024.data.repository.TrashRepositoryImpl
import com.tjrwns8024.domain.repository.PhotoRepository
import com.tjrwns8024.domain.repository.TrashRepository
import com.tjrwns8024.domain.usecase.GetTrashCanUseCase
import com.tjrwns8024.domain.usecase.GetTrashUseCase
import com.tjrwns8024.domain.usecase.UploadPhotoUseCase
import com.tjrwns8024.ttt_android.viewmodel.factory.MainViewModelFactory
import com.tjrwns8024.ttt_android.viewmodel.factory.PhotoViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.disposables.CompositeDisposable

@Module
@InstallIn(SingletonComponent::class)
class PhotoModule {

    @Provides
    fun providePhotoViewModelFactory(
        uploadPhotoUseCase: UploadPhotoUseCase
    ): PhotoViewModelFactory = PhotoViewModelFactory(uploadPhotoUseCase)

    @Provides
    fun providePhotoDataSource(
        api: Api
    ): PhotoDataSource = PhotoDataSourceImpl(api)

    @Provides
    fun providePhotoRepository(
        photoDataSource: PhotoDataSource
    ): PhotoRepository = PhotoRepositoryImpl(photoDataSource)

    @Provides
    fun provideUploadPhotoUseCase(
        photoRepository: PhotoRepository,
        compositeDisposable: CompositeDisposable
    ) = UploadPhotoUseCase(photoRepository, compositeDisposable)

}