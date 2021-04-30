package com.tjrwns8024.data.entity.response

import com.tjrwns8024.domain.entity.PhotoPath

data class PhotoPathData(
    val photoPath: String
)

fun PhotoPathData.toEntity(): PhotoPath =
    PhotoPath(
        photoPath
    )