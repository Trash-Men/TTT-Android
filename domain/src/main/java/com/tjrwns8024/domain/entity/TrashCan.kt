package com.tjrwns8024.domain.entity

data class TrashCanList(
    val trashCans: List<TrashCan>
)

data class TrashCan(
    val photoURL: String,
    val latitude: Float,
    val longitude: Float,
    val area: String,
    val address: String,
    val createdAt: String
)