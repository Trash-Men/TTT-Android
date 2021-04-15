package com.tjrwns8024.data.datasource

import com.tjrwns8024.data.base.getError
import com.tjrwns8024.data.entity.TrashListData
import com.tjrwns8024.data.remote.Api
import io.reactivex.Single

class TrashDataSourceImpl (
    private val api: Api
) : TrashDataSource {
    override fun getTrashList(): Single<TrashListData> =
        api.getTrashList().map { it.getError() }
}