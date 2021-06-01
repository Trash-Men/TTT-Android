package com.tjrwns8024.ttt_android.ui

import android.Manifest
import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.tjrwns8024.ttt_android.R
import com.tjrwns8024.ttt_android.base.BaseActivity
import com.tjrwns8024.ttt_android.databinding.ActivityMainBinding
import com.tjrwns8024.ttt_android.model.TrashModel
import com.tjrwns8024.ttt_android.util.CustomCalloutBalloonAdapter
import com.tjrwns8024.ttt_android.util.EventObserver
import com.tjrwns8024.ttt_android.util.ViewAnimation
import com.tjrwns8024.ttt_android.viewmodel.MainViewModel
import com.tjrwns8024.ttt_android.viewmodel.factory.MainViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import net.daum.mf.map.api.*
import splitties.activities.start
import splitties.toast.toast
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    @Inject
    lateinit var factory: MainViewModelFactory

    override val viewModel by lazy {
        ViewModelProvider(this, factory).get(MainViewModel::class.java)
    }

    override val layoutId = R.layout.activity_main

    lateinit var mapView: MapView

    companion object {
        val mapHash = HashMap<Int, TrashModel>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkPermission()
        drawMap()
    }

    override fun observeEvent() {
        with(viewModel) {
            isFabOpen.observe(this@MainActivity, {
                if (isFabOpen.value!!) {
                    ObjectAnimator.ofFloat(binding.photoFab, "translationY", 0f).apply { start() }
                    ObjectAnimator.ofFloat(binding.chartFab, "translationY", 0f).apply { start() }
                    ObjectAnimator.ofFloat(binding.rankFab, "translationY", 0f).apply { start() }
                } else {
                    ObjectAnimator.ofFloat(binding.photoFab, "translationY", -250f)
                        .apply { start() }
                    ObjectAnimator.ofFloat(binding.chartFab, "translationY", -500f)
                        .apply { start() }
                    ObjectAnimator.ofFloat(binding.rankFab, "translationY", -750f).apply { start() }
                }
                isRotate = ViewAnimation.rotateFab(binding.mainFab, !isRotate)
            })

            trashList.observe(this@MainActivity, {
                addMarker(trashList.value!!, 1)
            })

            trashCanList.observe(this@MainActivity, {
                addMarker(trashCanList.value!!, 2)
            })

            photoEvent.observe(this@MainActivity, {
                if (checkPhotoPermission()) {
                    start<ChoicePhotoActivity>()
                } else {
                    ActivityCompat.requestPermissions(
                        this@MainActivity,
                        arrayOf(
                            Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ), 1
                    )
                }
            })

            chartEvent.observe(this@MainActivity, {
                start<ChartActivity>()
            })
            rankEvent.observe(this@MainActivity, {
                start<RankActivity>()
            })
        }
    }

    private fun drawMap() {
        //mapView
        mapView = MapView(this)
        val mapViewContainer = binding.mapView
        mapViewContainer.addView(mapView)

        mapView.setCalloutBalloonAdapter(CustomCalloutBalloonAdapter(layoutInflater))

    }

    private fun addMarker(list: List<TrashModel>, num: Int) {
        for (data in list) {
            val trashCanMarker = MapPOIItem().apply {
                itemName = data.photoURL
                mapPoint = MapPoint.mapPointWithGeoCoord(
                    data.latitude.toDouble(),
                    data.longitude.toDouble()
                )
                tag = data.hashCode()
                markerType = MapPOIItem.MarkerType.CustomImage
                isCustomImageAutoscale = false
                setCustomImageAnchor(0.5f, 1.0f)
            }

            when (num) {
                1 -> trashCanMarker.customImageResourceId = R.drawable.ic_trash
                2 -> trashCanMarker.customImageResourceId = R.drawable.ic_trash_can
            }

            mapView.addPOIItem(trashCanMarker)
            mapHash[trashCanMarker.tag] = data
        }
    }

    private fun checkPermission() {
        try {
            if (ContextCompat.checkSelfPermission(
                    applicationContext,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ),
                    1
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun checkPhotoPermission(): Boolean {
        return (ContextCompat.checkSelfPermission(
            applicationContext,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(
            applicationContext,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED)
    }
}