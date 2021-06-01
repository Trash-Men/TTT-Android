package com.tjrwns8024.ttt_android.ui

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.tjrwns8024.ttt_android.R
import com.tjrwns8024.ttt_android.base.BaseActivity
import com.tjrwns8024.ttt_android.databinding.ActivitySendPhotoBinding
import com.tjrwns8024.ttt_android.util.EventObserver
import com.tjrwns8024.ttt_android.util.bitmapToFile
import com.tjrwns8024.ttt_android.util.getAddress
import com.tjrwns8024.ttt_android.viewmodel.PhotoViewModel
import com.tjrwns8024.ttt_android.viewmodel.factory.PhotoViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import splitties.activities.start
import splitties.systemservices.locationManager
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
            loadingEvent.observe(this@SendPhotoActivity, EventObserver {
                start<ResultPhotoActivity>()
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val typeString = intent.extras?.get("type")
        viewModel.type.value = typeString.toString()

        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)

        try {
            if (ContextCompat.checkSelfPermission(
                    applicationContext,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    101
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)

        viewModel.photoInfoModel.value?.latitude = location!!.latitude.toFloat()
        viewModel.photoInfoModel.value?.longitude = location.longitude.toFloat()
        viewModel.photoInfoModel.value?.area = getAddress(
            applicationContext,
            location.latitude.toFloat(),
            location.longitude.toFloat()
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap

            binding.imageView.setImageBitmap(imageBitmap)
            Log.d("hiddd",viewModel.type.value.toString())
            viewModel.photoModel.value?.photo = bitmapToFile(imageBitmap, "ttt-photo")!!
        }
    }
}