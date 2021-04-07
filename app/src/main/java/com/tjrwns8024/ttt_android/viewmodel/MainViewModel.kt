package com.tjrwns8024.ttt_android.viewmodel

import androidx.lifecycle.MutableLiveData
import com.tjrwns8024.ttt_android.base.BaseViewModel

class MainViewModel : BaseViewModel() {

    var isFabOpen = MutableLiveData(true)
    var isRotate = true

    fun toggleFab() {
        isFabOpen.value = !isFabOpen.value!!
    }
}