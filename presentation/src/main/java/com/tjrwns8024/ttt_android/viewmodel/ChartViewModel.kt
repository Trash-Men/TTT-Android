package com.tjrwns8024.ttt_android.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.tjrwns8024.domain.entity.TrashList
import com.tjrwns8024.domain.usecase.GetTrashCanUseCase
import com.tjrwns8024.domain.usecase.GetTrashUseCase
import com.tjrwns8024.ttt_android.base.BaseViewModel
import com.tjrwns8024.ttt_android.model.RankModel
import com.tjrwns8024.ttt_android.model.TrashModel
import com.tjrwns8024.ttt_android.model.toModel
import com.tjrwns8024.ttt_android.util.Event
import io.reactivex.observers.DisposableSingleObserver

class ChartViewModel(
    private val trashUseCase: GetTrashUseCase,
    private val trashCanUseCase: GetTrashCanUseCase
) : BaseViewModel() {

    var trashList = MutableLiveData<List<TrashModel>>()
    var trashCanList = MutableLiveData<List<TrashModel>>()

    val getTrashEvent = MutableLiveData<Event<Boolean>>()
    val getTrashCanEvent = MutableLiveData<Event<Boolean>>()

    val trashMap = HashMap<String, Int>()
    val trashCanMap = HashMap<String, Int>()

    init {
        getTrash()
        getTrashCan()
    }

    private fun getTrash() {
        trashUseCase.execute(Unit, object : DisposableSingleObserver<TrashList>() {
            override fun onSuccess(t: TrashList) {
                trashList.value = t.trashes.map { it.toModel() }

                for (i in trashList.value!!) {
                    if (trashMap.containsKey(i.area)) {
                        trashMap[i.area] = trashMap.getValue(i.area) + 1
                    } else {
                        trashMap[i.area] = 1
                    }
                }

                getTrashEvent.value = Event(true)
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

                for (i in trashCanList.value!!) {
                    if (trashCanMap.containsKey(i.area)) {
                        trashCanMap[i.area] = trashCanMap.getValue(i.area) + 1
                    } else {
                        trashCanMap[i.area] = 1
                    }
                }

                getTrashCanEvent.value = Event(true)
            }

            override fun onError(e: Throwable) {
                Log.e("erroror", e.toString())
            }
        })
    }

}