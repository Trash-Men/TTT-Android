package com.tjrwns8024.ttt_android.di.modules.chart

import com.tjrwns8024.domain.usecase.GetTrashCanUseCase
import com.tjrwns8024.domain.usecase.GetTrashUseCase
import com.tjrwns8024.ttt_android.viewmodel.factory.ChartViewModelFactory
import com.tjrwns8024.ttt_android.viewmodel.factory.MainViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ChartModule {
    @Provides
    fun provideChartViewModelFactory(
        trashUseCase: GetTrashUseCase,
        trashCanUseCase: GetTrashCanUseCase
    ): ChartViewModelFactory = ChartViewModelFactory(trashUseCase, trashCanUseCase)
}