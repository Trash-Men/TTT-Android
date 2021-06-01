package com.tjrwns8024.data.datasource

import com.tjrwns8024.data.entity.request.PhotoImageData
import com.tjrwns8024.data.entity.request.PhotoInfoData
import com.tjrwns8024.data.entity.response.PhotoPathData
import io.reactivex.Single

interface PhotoDataSource {
    fun uploadPhoto(photoImageData: PhotoImageData): Single<PhotoPathData>
    fun postTrashInfo(photoInfoData: PhotoInfoData): Single<Unit>
    fun postTrashCanInfo(photoInfoData: PhotoInfoData): Single<Unit>
}