package com.tjrwns8024.data.entity.request

import com.tjrwns8024.domain.entity.PhotoInfo

data class PhotoInfoData(
    val photoUrl: String,
    val latitude: Float,
    val longitude: Float,
    val area: String
)

fun PhotoInfo.toEntity(): PhotoInfoData =
    PhotoInfoData(
        photoUrl,
        latitude,
        longitude,
        area
    )