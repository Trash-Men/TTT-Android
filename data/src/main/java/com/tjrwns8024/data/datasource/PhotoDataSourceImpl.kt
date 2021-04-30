package com.tjrwns8024.data.datasource

import com.tjrwns8024.data.base.getError
import com.tjrwns8024.data.entity.request.PhotoData
import com.tjrwns8024.data.entity.response.PhotoPathData
import com.tjrwns8024.data.remote.Api
import com.tjrwns8024.data.util.toImageRequestBody
import io.reactivex.Single
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody

class PhotoDataSourceImpl(
    private val api: Api
) : PhotoDataSource{
    override fun uploadPhoto(photoData: PhotoData): Single<PhotoPathData> =
        api.uploadPhoto(
            photo = MultipartBody.Part.createFormData("photo", photoData.photo.toString() , photoData.photo.toImageRequestBody()),
            type = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), photoData.type)
        ).map { it.getError() }
}