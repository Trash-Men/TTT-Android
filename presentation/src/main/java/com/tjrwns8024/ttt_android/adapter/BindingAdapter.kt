package com.tjrwns8024.ttt_android.adapter

import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.tjrwns8024.ttt_android.model.RankModel

@BindingAdapter("rankAdapter")
fun RecyclerView.bindOutingList(rankItems: MutableLiveData<ArrayList<RankModel>>) {
    (adapter as RankAdapter).setItems(rankItems)
}