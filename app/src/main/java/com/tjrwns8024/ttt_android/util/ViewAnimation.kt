package com.tjrwns8024.ttt_android.util

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View

class ViewAnimation {
    companion object{
        fun rotateFab(v: View, rotate: Boolean): Boolean {
            v.animate().setDuration(200)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        super.onAnimationEnd(animation)
                    }
                })
                .rotation(if (rotate) 45f else 0f)
            return rotate
        }
    }
}