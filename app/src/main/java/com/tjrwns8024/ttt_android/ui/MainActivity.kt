package com.tjrwns8024.ttt_android.ui

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import com.tjrwns8024.ttt_android.R
import com.tjrwns8024.ttt_android.base.BaseActivity
import com.tjrwns8024.ttt_android.databinding.ActivityMainBinding
import com.tjrwns8024.ttt_android.util.ViewAnimation
import net.daum.mf.map.api.MapView
import splitties.activities.start

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layoutId: Int
        get() = R.layout.activity_main

    private var isFabOpen = false
    private var isRotate = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.main = this

        binding.mainFab.setOnClickListener {
            toggleFab()
            isRotate = ViewAnimation.rotateFab(it, !isRotate)
        }

        //mapView
        val mapView = MapView(this)
        val mapViewContainer = binding.mapView
        mapViewContainer.addView(mapView)
    }

    private fun toggleFab() {
        if (isFabOpen) {
            ObjectAnimator.ofFloat(binding.pictureFab, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(binding.chartFab, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(binding.rankFab, "translationY", 0f).apply { start() }
        } else {
            ObjectAnimator.ofFloat(binding.pictureFab, "translationY", -250f).apply { start() }
            ObjectAnimator.ofFloat(binding.chartFab, "translationY", -500f).apply { start() }
            ObjectAnimator.ofFloat(binding.rankFab, "translationY", -750f).apply { start() }
        }

        isFabOpen = !isFabOpen

    }

    fun clickRankActivity(view: View){
        start<RankActivity>()
    }

}