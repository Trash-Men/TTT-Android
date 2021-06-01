package com.tjrwns8024.ttt_android.di.modules.rank

import com.tjrwns8024.domain.usecase.GetTrashCanUseCase
import com.tjrwns8024.domain.usecase.GetTrashUseCase
import com.tjrwns8024.ttt_android.viewmodel.factory.RankViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RankModule {
    @Provides
    fun provideChartViewModelFactory(
        trashUseCase: GetTrashUseCase,
        trashCanUseCase: GetTrashCanUseCase
    ): RankViewModelFactory = RankViewModelFactory(trashUseCase, trashCanUseCase)
}