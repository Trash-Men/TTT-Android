package com.tjrwns8024.data.remote

import com.tjrwns8024.data.entity.request.PhotoInfoData
import com.tjrwns8024.data.entity.response.PhotoPathData
import com.tjrwns8024.data.entity.response.TrashListData
import com.tjrwns8024.data.entity.response.TrashCanListData
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface Api {
    @GET("/trash/all")
    fun getTrashList(): Single<Response<TrashListData>>

    @GET("/trash-can/all")
    fun getTrashCanList(): Single<Response<TrashCanListData>>

    @Multipart
    @POST("/photo")
    fun uploadPhoto(
        @Part photo: MultipartBody.Part,
        @Part("type") type: RequestBody
    ): Single<Response<PhotoPathData>>

    @POST("/trash")
    fun postTrashInfo(@Body photoInfoData: PhotoInfoData): Single<Response<Unit>>

    @POST("/trash-can")
    fun postTrashCanInfo(@Body photoInfoData: PhotoInfoData): Single<Response<Unit>>
}