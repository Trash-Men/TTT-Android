package com.tjrwns8024.ttt_android.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.tjrwns8024.ttt_android.base.BaseViewModel
import com.tjrwns8024.domain.entity.TrashList
import com.tjrwns8024.domain.usecase.GetTrashUseCase
import io.reactivex.observers.DisposableSingleObserver

class MainViewModel(
    private val trashUseCase: GetTrashUseCase
) : BaseViewModel() {

    init {
        getTrash()
    }

    var isFabOpen = MutableLiveData(true)
    var isRotate = true

    fun toggleFab() {
        isFabOpen.value = !isFabOpen.value!!
    }

    private fun getTrash() {
        trashUseCase.execute(Unit, object : DisposableSingleObserver<TrashList>() {
            override fun onSuccess(t: TrashList) {
                Log.d("asdfa",t.trashes.toString())
            }

            override fun onError(e: Throwable) {
                Log.e("erroror",e.toString())
            }
        })
    }
}