package com.tjrwns8024.ttt_android.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.tjrwns8024.ttt_android.R
import com.tjrwns8024.ttt_android.databinding.ActivityResultPhotoBinding

class ResultPhotoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_photo)

        val binding : ActivityResultPhotoBinding = DataBindingUtil.setContentView(this, R.layout.activity_result_photo)

        binding.goToMainTv.setOnClickListener {
            Log.d("hihihihih", "asfdasdfasdfasdfasdf")
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }

    }

}