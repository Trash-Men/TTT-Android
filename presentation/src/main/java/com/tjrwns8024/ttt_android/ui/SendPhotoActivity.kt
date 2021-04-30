package com.tjrwns8024.ttt_android.ui

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.tjrwns8024.ttt_android.R
import com.tjrwns8024.ttt_android.base.BaseActivity
import com.tjrwns8024.ttt_android.databinding.ActivitySendPhotoBinding
import com.tjrwns8024.ttt_android.util.EventObserver
import com.tjrwns8024.ttt_android.util.bitmapToFile
import com.tjrwns8024.ttt_android.viewmodel.PhotoViewModel
import com.tjrwns8024.ttt_android.viewmodel.factory.PhotoViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SendPhotoActivity : BaseActivity<ActivitySendPhotoBinding>() {

    private val REQUEST_IMAGE_CAPTURE = 1

    @Inject
    lateinit var factory: PhotoViewModelFactory

    override val layoutId: Int = R.layout.activity_send_photo

    override val viewModel by lazy {
        ViewModelProvider(this, factory).get(PhotoViewModel::class.java)
    }

    override fun observeEvent() {
        with(viewModel) {
            photoEvent.observe(this@SendPhotoActivity, EventObserver {
                val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            })
            backEvent.observe(this@SendPhotoActivity, EventObserver {
                this@SendPhotoActivity.finish()
            })

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap

            binding.imageView.setImageBitmap(imageBitmap)

            viewModel.photoModel.value?.photo = bitmapToFile(imageBitmap, "ttt-photo")!!
            viewModel.photoModel.value?.type = "trash"
        }
    }
}