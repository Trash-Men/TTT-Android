package com.tjrwns8024.domain.repository

import com.tjrwns8024.domain.entity.PhotoImage
import com.tjrwns8024.domain.entity.PhotoInfo
import com.tjrwns8024.domain.entity.PhotoPath
import io.reactivex.Single

interface PhotoRepository {
    fun uploadPhoto(photoImage: PhotoImage): Single<PhotoPath>
    fun postTrashInfo(photoInfo: PhotoInfo): Single<Unit>
    fun postTrashCanInfo(photoInfo: PhotoInfo): Single<Unit>
}