package com.tjrwns8024.ttt_android.model

import com.tjrwns8024.domain.entity.TrashCan

data class TrashCanModel(
    val photoURL: String,
    val latitude: Float,
    val longitude: Float,
    val area: String,
    val address: String,
    val createdAt: String
)

fun TrashCan.toModel(): TrashCanModel =
    TrashCanModel(
        photoURL,
        latitude,
        longitude,
        area,
        address,
        createdAt
    )
