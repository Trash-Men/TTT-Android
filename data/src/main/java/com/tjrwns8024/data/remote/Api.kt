package com.tjrwns8024.data.remote

import com.tjrwns8024.data.entity.TrashCanListData
import com.tjrwns8024.data.entity.TrashListData
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface Api {
    @GET("/trash/all")
    fun getTrashList(): Single<Response<TrashListData>>

    @GET("/trash-can/all")
    fun getTrashCanList(): Single<Response<TrashCanListData>>
}