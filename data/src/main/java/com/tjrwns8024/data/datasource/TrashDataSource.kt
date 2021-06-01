package com.tjrwns8024.data.datasource

import com.tjrwns8024.data.entity.response.TrashCanListData
import com.tjrwns8024.data.entity.response.TrashListData
import io.reactivex.Single

interface TrashDataSource {
    fun getTrashList(): Single<TrashListData>
    fun getTrashCanList(): Single<TrashCanListData>
}