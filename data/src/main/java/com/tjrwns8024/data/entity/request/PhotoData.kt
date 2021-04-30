package com.tjrwns8024.data.entity.request

import com.tjrwns8024.domain.entity.Photo
import java.io.File

data class PhotoData(
    val photo: File,
    val type: String
)

fun Photo.toEntity(): PhotoData =
    PhotoData(
        photo,
        type
    )