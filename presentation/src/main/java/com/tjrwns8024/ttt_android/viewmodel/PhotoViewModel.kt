package com.tjrwns8024.ttt_android.viewmodel

import androidx.lifecycle.MutableLiveData
import com.tjrwns8024.ttt_android.base.BaseViewModel
import com.tjrwns8024.ttt_android.util.Event

class PhotoViewModel : BaseViewModel() {
    val backEvent = MutableLiveData<Event<Boolean>>()

    fun clickBack() {
        backEvent.value = Event(true)
    }
}