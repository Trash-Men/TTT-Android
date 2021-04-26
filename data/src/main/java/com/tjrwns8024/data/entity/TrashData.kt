package com.tjrwns8024.data.entity

import com.google.gson.annotations.SerializedName
import com.tjrwns8024.domain.entity.Trash
import com.tjrwns8024.domain.entity.TrashList

data class TrashListData(
    val trashes: List<TrashData>
)

data class TrashData(
    @SerializedName("photo_url")
    val photoURL: String,
    @SerializedName("latitude")
    val latitude: Float,
    @SerializedName("longitude")
    val longitude: Float,
    @SerializedName("area")
    val area: String,
    @SerializedName("created_at")
    val createdAt: String
)

fun TrashListData.toEntity(): TrashList =
    TrashList(trashes.map {
        it.toEntity()
    })

fun TrashData.toEntity(): Trash =
    Trash(
        photoURL,
        latitude,
        longitude,
        area,
        createdAt
    )