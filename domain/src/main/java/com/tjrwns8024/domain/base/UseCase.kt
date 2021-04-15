package com.tjrwns8024.domain.base

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

abstract class UseCase<T, R>(
    private val compositeDisposable: CompositeDisposable
) {
    abstract fun createObservable(data: T): Single<R>

    fun execute(data: T, observer: DisposableSingleObserver<R>) {
        compositeDisposable.add(
            createObservable(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer)
        )
    }
}