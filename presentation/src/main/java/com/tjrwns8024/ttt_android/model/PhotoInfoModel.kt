package com.tjrwns8024.ttt_android.model

import com.tjrwns8024.domain.entity.PhotoInfo

data class PhotoInfoModel(
    var photoUrl: String = "",
    var latitude: Float = 0.0F,
    var longitude: Float = 0.0F,
    var area: String = ""
)

fun PhotoInfoModel.toEntity(): PhotoInfo =
    PhotoInfo(
        photoUrl,
        latitude,
        longitude,
        area
    )