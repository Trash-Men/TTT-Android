package com.tjrwns8024.ttt_android.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.tjrwns8024.domain.usecase.UploadPhotoUseCase
import com.tjrwns8024.ttt_android.base.BaseViewModel
import com.tjrwns8024.ttt_android.model.PhotoModel
import com.tjrwns8024.ttt_android.model.toEntity
import com.tjrwns8024.ttt_android.util.Event
import io.reactivex.observers.DisposableSingleObserver

class PhotoViewModel(
    private val uploadPhotoUseCase: UploadPhotoUseCase
) : BaseViewModel() {
    val backEvent = MutableLiveData<Event<Boolean>>()
    val photoEvent = MutableLiveData<Event<Boolean>>()
    val nextEvent = MutableLiveData<Event<Boolean>>()

    val photoModel = MutableLiveData<PhotoModel>().apply {
        value = PhotoModel()
    }

    val photoURI = MutableLiveData<String>()

    fun clickBack() {
        backEvent.value = Event(true)
    }

    fun clickNext() {
        nextEvent.value = Event(true)
    }

    fun clickPhoto() {
        photoEvent.value = Event(true)
    }

    fun sendPhoto() {
        Log.d("hihihihihi", photoModel.value?.type.toString())
        Log.d("hihihihihi", photoModel.value?.photo.toString())

        uploadPhotoUseCase.execute(photoModel.value!!.toEntity() , object: DisposableSingleObserver<Unit>(){
            override fun onSuccess(t: Unit) {
                Log.d("success",t.toString() + "dsf")
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
            }

        })
    }
}