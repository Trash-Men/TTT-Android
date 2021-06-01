package com.tjrwns8024.ttt_android.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tjrwns8024.domain.usecase.PostTrashCanInfoUseCase
import com.tjrwns8024.domain.usecase.PostTrashInfoUseCase
import com.tjrwns8024.domain.usecase.UploadPhotoUseCase

class PhotoViewModelFactory(
    private val uploadPhotoUseCase: UploadPhotoUseCase,
    private val postTrashInfoUseCase: PostTrashInfoUseCase,
    private val postTrashCanInfoUseCase: PostTrashCanInfoUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        modelClass.getConstructor(UploadPhotoUseCase::class.java, PostTrashInfoUseCase::class.java, PostTrashCanInfoUseCase::class.java)
            .newInstance(uploadPhotoUseCase, postTrashInfoUseCase, postTrashCanInfoUseCase)
}