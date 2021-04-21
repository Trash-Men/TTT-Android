package com.tjrwns8024.domain.entity

data class TrashList(
    val trashes: List<Trash>
)

data class Trash(
    val photoURL: String,
    val latitude: Float,
    val longitude: Float,
    val area: String,
    val createdAt: String
)