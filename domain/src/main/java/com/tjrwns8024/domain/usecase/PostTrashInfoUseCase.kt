package com.tjrwns8024.domain.usecase

import com.tjrwns8024.domain.base.UseCase
import com.tjrwns8024.domain.entity.PhotoInfo
import com.tjrwns8024.domain.repository.PhotoRepository
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class PostTrashInfoUseCase(
    private val photoRepository: PhotoRepository,
    private val compositeDisposable: CompositeDisposable
) : UseCase<PhotoInfo, Unit>(compositeDisposable) {
    override fun createObservable(data: PhotoInfo): Single<Unit> =
        photoRepository.postTrashInfo(data)
}