package com.tjrwns8024.domain.usecase

import com.tjrwns8024.domain.base.UseCase
import com.tjrwns8024.domain.entity.PhotoImage
import com.tjrwns8024.domain.entity.PhotoPath
import com.tjrwns8024.domain.repository.PhotoRepository
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class UploadPhotoUseCase(
    private val photoRepository: PhotoRepository,
    private val compositeDisposable: CompositeDisposable
) : UseCase<PhotoImage, PhotoPath>(compositeDisposable) {
    override fun createObservable(data: PhotoImage): Single<PhotoPath> =
        photoRepository.uploadPhoto(data)
}