package com.tjrwns8024.domain.entity

import java.io.File

data class PhotoImage(
    val photo: File,
    val type: String
)

data class PhotoPath(
    val photoPath: String
)