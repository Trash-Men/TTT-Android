package com.tjrwns8024.data.base

import retrofit2.Response
import java.lang.RuntimeException

fun <T> Response<T>.getError(): T {
    return when (this.code() / 100) {
        2 -> this.body()!!
        else -> throw RuntimeException("${this.code()} => ${this.message()}")
    }
}