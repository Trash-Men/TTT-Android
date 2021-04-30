package com.tjrwns8024.ttt_android.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tjrwns8024.domain.usecase.UploadPhotoUseCase

class PhotoViewModelFactory(
    private val uploadPhotoUseCase: UploadPhotoUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        modelClass.getConstructor(UploadPhotoUseCase::class.java).newInstance(uploadPhotoUseCase)
}