package com.tjrwns8024.domain.repository

import com.tjrwns8024.domain.entity.Photo
import com.tjrwns8024.domain.entity.PhotoPath
import io.reactivex.Single
import java.io.File

interface PhotoRepository {
    fun uploadPhoto(photo: Photo): Single<PhotoPath>
}