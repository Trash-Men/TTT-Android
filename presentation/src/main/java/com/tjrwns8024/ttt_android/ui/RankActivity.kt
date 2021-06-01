package com.tjrwns8024.ttt_android.ui

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tjrwns8024.ttt_android.R
import com.tjrwns8024.ttt_android.adapter.RankAdapter
import com.tjrwns8024.ttt_android.base.BaseActivity
import com.tjrwns8024.ttt_android.base.BaseViewModel
import com.tjrwns8024.ttt_android.databinding.ActivityRankBinding
import com.tjrwns8024.ttt_android.viewmodel.ChartViewModel
import com.tjrwns8024.ttt_android.viewmodel.MainViewModel
import com.tjrwns8024.ttt_android.viewmodel.RankViewModel
import com.tjrwns8024.ttt_android.viewmodel.factory.ChartViewModelFactory
import com.tjrwns8024.ttt_android.viewmodel.factory.RankViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RankActivity : BaseActivity<ActivityRankBinding>() {

    @Inject
    lateinit var factory: RankViewModelFactory

    override val layoutId = R.layout.activity_rank

    override val viewModel by lazy {
        ViewModelProvider(this, factory).get(RankViewModel::class.java)
    }
    override fun observeEvent() {
        with(viewModel){
            getTrashEvent.observe(this@RankActivity, {

            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.trashRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
        binding.trashRecyclerView.adapter = RankAdapter()
    }
}