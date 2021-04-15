package com.tjrwns8024.data.repository

import com.tjrwns8024.data.datasource.TrashDataSource
import com.tjrwns8024.data.entity.toEntity
import com.tjrwns8024.domain.entity.TrashList
import com.tjrwns8024.domain.repository.TrashRepository
import io.reactivex.Single

class TrashRepositoryImpl(
    private val trashDataSource: TrashDataSource
) : TrashRepository {
    override fun getTrashList(): Single<TrashList> =
        trashDataSource.getTrashList().map { it.toEntity() }
}