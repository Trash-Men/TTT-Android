package com.tjrwns8024.data.repository

import com.tjrwns8024.data.datasource.TrashDataSource
import com.tjrwns8024.data.entity.response.toEntity
import com.tjrwns8024.domain.entity.TrashList
import com.tjrwns8024.domain.repository.TrashRepository
import io.reactivex.Single

class TrashRepositoryImpl(
    private val trashDataSource: TrashDataSource
) : TrashRepository {
    override fun getTrashList(): Single<TrashList> =
        trashDataSource.getTrashList().map { it.toEntity() }

    override fun getTrashCanList(): Single<TrashList> =
        trashDataSource.getTrashCanList().map { it.toEntity() }
}