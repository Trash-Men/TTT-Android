package com.tjrwns8024.ttt_android.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.tjrwns8024.ttt_android.base.BaseViewModel
import com.tjrwns8024.domain.entity.TrashList
import com.tjrwns8024.domain.usecase.GetTrashCanUseCase
import com.tjrwns8024.domain.usecase.GetTrashUseCase
import com.tjrwns8024.ttt_android.model.TrashModel
import com.tjrwns8024.ttt_android.model.toModel
import io.reactivex.observers.DisposableSingleObserver

class MainViewModel(
    private val trashUseCase: GetTrashUseCase,
    private val trashCanUseCase: GetTrashCanUseCase
) : BaseViewModel() {

    init {
        getTrash()
        getTrashCan()
    }

    var trashList = MutableLiveData<List<TrashModel>>()
    var trashCanList = MutableLiveData<List<TrashModel>>()
    var isFabOpen = MutableLiveData(true)
    var isRotate = true

    fun toggleFab() {
        isFabOpen.value = !isFabOpen.value!!
    }

    private fun getTrash() {
        trashUseCase.execute(Unit, object : DisposableSingleObserver<TrashList>() {
            override fun onSuccess(t: TrashList) {
                trashList.value = t.trashes.map { it.toModel() }
                Log.d("success",trashList.value.toString())
            }

            override fun onError(e: Throwable) {
                Log.e("erroror", e.toString())
            }
        })
    }

    private fun getTrashCan() {
        trashCanUseCase.execute(Unit, object : DisposableSingleObserver<TrashList>() {
            override fun onSuccess(t: TrashList) {
                trashCanList.value = t.trashes.map { it.toModel() }
                Log.d("success",trashCanList.value.toString())
            }

            override fun onError(e: Throwable) {
                Log.e("erroror", e.toString())
            }
        })
    }
}