package com.tjrwns8024.ttt_android.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.tjrwns8024.ttt_android.R

class ResultPhotoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_photo)

        val a = findViewById<LottieAnimationView>(R.id.loading_lottie)

        a.playAnimation()
    }
}