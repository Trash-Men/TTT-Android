package com.tjrwns8024.ttt_android.model

import com.tjrwns8024.domain.entity.PhotoImage
import java.io.File

data class PhotoModel(
    var photo: File = File(""),
    var type: String = ""
)

fun PhotoModel.toEntity(): PhotoImage =
    PhotoImage(
        photo,
        type
    )