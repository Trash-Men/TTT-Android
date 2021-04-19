package com.tjrwns8024.data.entity

import com.google.gson.annotations.SerializedName
import com.tjrwns8024.domain.entity.TrashCan
import com.tjrwns8024.domain.entity.TrashCanList

data class TrashCanListData(
    val trashCans: List<TrashCanData>
)

data class TrashCanData(
    @SerializedName("photo_url")
    val photoURL: String,
    @SerializedName("latitude")
    val latitude: Float,
    @SerializedName("longitude")
    val longitude: Float,
    @SerializedName("area")
    val area: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("created_at")
    val createdAt: String
)

fun TrashCanListData.toEntity(): TrashCanList =
    TrashCanList(trashCans.map {
        it.toEntity()
    })

fun TrashCanData.toEntity(): TrashCan =
    TrashCan(
        photoURL,
        latitude,
        longitude,
        area,
        address,
        createdAt
    )