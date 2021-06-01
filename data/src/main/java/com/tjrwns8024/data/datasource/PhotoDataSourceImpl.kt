package com.tjrwns8024.data.datasource

import android.util.Log
import com.tjrwns8024.data.base.getError
import com.tjrwns8024.data.entity.request.PhotoImageData
import com.tjrwns8024.data.entity.request.PhotoInfoData
import com.tjrwns8024.data.entity.response.PhotoPathData
import com.tjrwns8024.data.remote.Api
import com.tjrwns8024.data.util.toImageRequestBody
import io.reactivex.Single
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody

class PhotoDataSourceImpl(
    private val api: Api
) : PhotoDataSource {
    override fun uploadPhoto(photoImageData: PhotoImageData): Single<PhotoPathData> {
        Log.d("loglog2", photoImageData.type)

        return api.uploadPhoto(
            photo = MultipartBody.Part.createFormData(
                "photo",
                photoImageData.photo.toString(),
                photoImageData.photo.toImageRequestBody()
            ),
            type = RequestBody.create(
                "multipart/form-data".toMediaTypeOrNull(),
                photoImageData.type
            )
        ).map { it.getError() }
    }

    override fun postTrashInfo(photoInfoData: PhotoInfoData): Single<Unit> =
        api.postTrashInfo(photoInfoData).map { it.getError() }

    override fun postTrashCanInfo(photoInfoData: PhotoInfoData): Single<Unit> =
        api.postTrashCanInfo(photoInfoData).map { it.getError() }
}