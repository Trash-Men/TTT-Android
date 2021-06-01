package com.tjrwns8024.data.repository

import com.tjrwns8024.data.datasource.PhotoDataSource
import com.tjrwns8024.data.entity.request.toEntity
import com.tjrwns8024.data.entity.response.toEntity
import com.tjrwns8024.domain.entity.PhotoImage
import com.tjrwns8024.domain.entity.PhotoInfo
import com.tjrwns8024.domain.entity.PhotoPath
import com.tjrwns8024.domain.repository.PhotoRepository
import io.reactivex.Single

class PhotoRepositoryImpl(
    private val photoDataSource: PhotoDataSource
) : PhotoRepository {
    override fun uploadPhoto(photoImage: PhotoImage): Single<PhotoPath> =
        photoDataSource.uploadPhoto(photoImage.toEntity()).map { it.toEntity() }

    override fun postTrashInfo(photoInfo: PhotoInfo): Single<Unit> =
        photoDataSource.postTrashInfo(photoInfo.toEntity())

    override fun postTrashCanInfo(photoInfo: PhotoInfo): Single<Unit> =
        photoDataSource.postTrashCanInfo(photoInfo.toEntity())
}