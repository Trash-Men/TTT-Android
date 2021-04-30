package com.tjrwns8024.data.repository

import com.tjrwns8024.data.datasource.PhotoDataSource
import com.tjrwns8024.data.entity.request.toEntity
import com.tjrwns8024.data.entity.response.toEntity
import com.tjrwns8024.domain.entity.Photo
import com.tjrwns8024.domain.entity.PhotoPath
import com.tjrwns8024.domain.repository.PhotoRepository
import io.reactivex.Single

class PhotoRepositoryImpl(
    private val photoDataSource: PhotoDataSource
) : PhotoRepository {
    override fun uploadPhoto(photo: Photo): Single<PhotoPath> =
        photoDataSource.uploadPhoto(photo.toEntity()).map { it.toEntity() }
}