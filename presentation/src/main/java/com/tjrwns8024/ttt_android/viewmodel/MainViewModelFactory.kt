package com.tjrwns8024.ttt_android.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tjrwns8024.domain.usecase.GetTrashUseCase

class MainViewModelFactory(
    private val trashUseCase: GetTrashUseCase
) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        modelClass.getConstructor(GetTrashUseCase::class.java).newInstance(trashUseCase)
}