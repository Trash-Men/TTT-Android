package com.tjrwns8024.ttt_android.base

sealed class Result<T> {
    data class Success<T>(val value: T) : Result<T>()

    data class Failure<T>(val throwable: Throwable) : Result<T>()
}