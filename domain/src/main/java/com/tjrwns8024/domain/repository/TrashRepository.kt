package com.tjrwns8024.domain.repository

import com.tjrwns8024.domain.entity.TrashList
import io.reactivex.Single

interface TrashRepository {
    fun getTrashList(): Single<TrashList>
    fun getTrashCanList(): Single<TrashList>
}