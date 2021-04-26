package com.tjrwns8024.ttt_android.model

import com.tjrwns8024.domain.entity.Trash

data class TrashModel(
    val photoURL: String,
    val latitude: Float,
    val longitude: Float,
    val area: String,
    val createdAt: String
)

fun Trash.toModel(): TrashModel =
    TrashModel(
        photoURL,
        latitude,
        longitude,
        area,
        createdAt
    )
