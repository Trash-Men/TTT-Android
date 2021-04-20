package com.tjrwns8024.domain.usecase

import com.tjrwns8024.domain.base.UseCase
import com.tjrwns8024.domain.entity.TrashList
import com.tjrwns8024.domain.repository.TrashRepository
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class GetTrashCanUseCase(
    private val trashRepository: TrashRepository,
    compositeDisposable: CompositeDisposable
) : UseCase<Unit, TrashList>(compositeDisposable) {
    override fun createObservable(data: Unit): Single<TrashList> =
        trashRepository.getTrashCanList()
}