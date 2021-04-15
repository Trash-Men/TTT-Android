package com.tjrwns8024.ttt_android.di.modules.trash

import com.tjrwns8024.data.datasource.TrashDataSource
import com.tjrwns8024.data.datasource.TrashDataSourceImpl
import com.tjrwns8024.data.remote.Api
import com.tjrwns8024.domain.repository.TrashRepository
import com.tjrwns8024.data.repository.TrashRepositoryImpl
import com.tjrwns8024.domain.usecase.GetTrashUseCase
import com.tjrwns8024.ttt_android.viewmodel.MainViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.disposables.CompositeDisposable

@Module
@InstallIn(SingletonComponent::class)
class TrashModule {

    @Provides
    fun provideTrashDataSource(
        api: Api
    ): TrashDataSource = TrashDataSourceImpl(api)

    @Provides
    fun provideTrashRepository(
        trashDataSource: TrashDataSource
    ): TrashRepository = TrashRepositoryImpl(trashDataSource)

    @Provides
    fun provideGetTrashUseCase(
        trashRepository: TrashRepository,
        compositeDisposable: CompositeDisposable
    ) = GetTrashUseCase(trashRepository, compositeDisposable)

    @Provides
    fun provideMainViewModelFactory(
        trashUseCase: GetTrashUseCase
    ): MainViewModelFactory = MainViewModelFactory((trashUseCase))
}