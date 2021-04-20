package com.tjrwns8024.data.datasource

import com.tjrwns8024.data.entity.TrashCanListData
import com.tjrwns8024.data.entity.TrashListData
import io.reactivex.Single

interface TrashDataSource {
    fun getTrashList(): Single<TrashListData>
    fun getTrashCanList(): Single<TrashCanListData>
}