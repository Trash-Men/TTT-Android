package com.tjrwns8024.data.datasource

import com.tjrwns8024.data.entity.request.PhotoData
import com.tjrwns8024.data.entity.response.PhotoPathData
import io.reactivex.Single

interface PhotoDataSource {
    fun uploadPhoto(photoData: PhotoData): Single<PhotoPathData>
}