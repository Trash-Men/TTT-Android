package com.tjrwns8024.data.entity.request

import com.tjrwns8024.domain.entity.PhotoImage
import java.io.File

data class PhotoImageData(
    val photo: File,
    val type: String
)

fun PhotoImage.toEntity(): PhotoImageData =
    PhotoImageData(
        photo,
        type
    )