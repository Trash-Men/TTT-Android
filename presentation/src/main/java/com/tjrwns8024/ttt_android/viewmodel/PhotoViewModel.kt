package com.tjrwns8024.ttt_android.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.tjrwns8024.domain.entity.PhotoPath
import com.tjrwns8024.domain.usecase.PostTrashCanInfoUseCase
import com.tjrwns8024.domain.usecase.PostTrashInfoUseCase
import com.tjrwns8024.domain.usecase.UploadPhotoUseCase
import com.tjrwns8024.ttt_android.base.BaseViewModel
import com.tjrwns8024.ttt_android.model.PhotoInfoModel
import com.tjrwns8024.ttt_android.model.PhotoModel
import com.tjrwns8024.ttt_android.model.toEntity
import com.tjrwns8024.ttt_android.util.Event
import io.reactivex.observers.DisposableSingleObserver
import splitties.toast.toast

class PhotoViewModel(
    private val uploadPhotoUseCase: UploadPhotoUseCase,
    private val postTrashInfoUseCase: PostTrashInfoUseCase,
    private val postTrashCanInfoUseCase: PostTrashCanInfoUseCase
) : BaseViewModel() {
    val backEvent = MutableLiveData<Event<Boolean>>()
    val photoEvent = MutableLiveData<Event<Boolean>>()
    val nextEvent = MutableLiveData<Event<Boolean>>()
    val loadingEvent = MutableLiveData<Event<Boolean>>()
    val type = MutableLiveData<String>()

    val photoModel = MutableLiveData<PhotoModel>().apply {
        value = PhotoModel()
    }
    val photoInfoModel = MutableLiveData<PhotoInfoModel>().apply {
        value = PhotoInfoModel()
    }

    fun sendPhoto() {
        photoModel.value?.type = type.value.toString()

        uploadPhotoUseCase.execute(photoModel.value!!.toEntity() , object: DisposableSingleObserver<PhotoPath>(){
            override fun onSuccess(t: PhotoPath) {
                photoInfoModel.value?.photoUrl = t.photoPath
                when(type.value){
                    "trash" -> postTrashInfo()
                    "trashCan" -> postTrashCanInfo()
                }
            }
            override fun onError(e: Throwable) {
                e.printStackTrace()
            }
        })
    }

    fun postTrashInfo() {
        postTrashInfoUseCase.execute(photoInfoModel.value!!.toEntity() , object : DisposableSingleObserver<Unit>() {
            override fun onSuccess(t: Unit) {
                toast("전송 성공")

                loadingEvent.value = Event(true)
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
            }
        })
    }

    fun postTrashCanInfo() {
        postTrashCanInfoUseCase.execute(photoInfoModel.value!!.toEntity() , object : DisposableSingleObserver<Unit>() {
            override fun onSuccess(t: Unit) {
                toast("전송 성공")

                loadingEvent.value = Event(true)
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
            }
        })
    }

    fun onSplitTypeChanged(id: Int) {
        when(id){
            1 -> type.value = "trash"
            2 -> type.value = "trashCan"
        }
    }

    fun clickBack() {
        backEvent.value = Event(true)
    }

    fun clickNext() {
        nextEvent.value = Event(true)
    }

    fun clickPhoto() {
        photoEvent.value = Event(true)
    }
}