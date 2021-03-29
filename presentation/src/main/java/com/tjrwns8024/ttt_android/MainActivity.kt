package com.tjrwns8024.ttt_android

import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.tjrwns8024.ttt_android.databinding.ActivityMainBinding
import com.tjrwns8024.ttt_android.util.ViewAnimation

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private var isFabOpen = false
    private var isRotate = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.main = this

        binding.mainFab.setOnClickListener {
            toggleFab()
            isRotate = ViewAnimation.rotateFab(it, !isRotate)
        }

        //전체화면
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

            window.attributes.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        }

    }

    private fun toggleFab() {
        if (isFabOpen) {
            ObjectAnimator.ofFloat(binding.pictureFab, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(binding.chartFab, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(binding.rankFab, "translationY", 0f).apply { start() }
        } else {
            ObjectAnimator.ofFloat(binding.pictureFab, "translationY", -200f).apply { start() }
            ObjectAnimator.ofFloat(binding.chartFab, "translationY", -400f).apply { start() }
            ObjectAnimator.ofFloat(binding.rankFab, "translationY", -600f).apply { start() }
        }

        isFabOpen = !isFabOpen

    }
}