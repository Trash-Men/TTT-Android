package com.tjrwns8024.data.entity.response

import com.tjrwns8024.domain.entity.TrashList

data class TrashCanListData(
    val trashCans: List<TrashData>
)

fun TrashCanListData.toEntity(): TrashList =
    TrashList(trashCans.map {
        it.toEntity()
    })