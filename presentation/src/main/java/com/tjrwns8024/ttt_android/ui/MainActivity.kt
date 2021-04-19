package com.tjrwns8024.ttt_android.ui

import android.Manifest
import android.animation.ObjectAnimator
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.tjrwns8024.ttt_android.R
import com.tjrwns8024.ttt_android.base.BaseActivity
import com.tjrwns8024.ttt_android.databinding.ActivityMainBinding
import com.tjrwns8024.ttt_android.util.ViewAnimation
import com.tjrwns8024.ttt_android.viewmodel.MainViewModel
import com.tjrwns8024.ttt_android.viewmodel.MainViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import net.daum.mf.map.api.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(
    R.layout.activity_main
) {

    @Inject
    lateinit var factory: MainViewModelFactory

    override val viewModel by lazy {
        ViewModelProvider(this, factory).get(MainViewModel::class.java)
    }

    lateinit var mapView: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        drawMap()
    }

    override fun observeEvent() {
        with(viewModel) {
            isFabOpen.observe(this@MainActivity, {
                if (isFabOpen.value!!) {
                    ObjectAnimator.ofFloat(binding.pictureFab, "translationY", 0f).apply { start() }
                    ObjectAnimator.ofFloat(binding.chartFab, "translationY", 0f).apply { start() }
                    ObjectAnimator.ofFloat(binding.rankFab, "translationY", 0f).apply { start() }
                } else {
                    ObjectAnimator.ofFloat(binding.pictureFab, "translationY", -250f)
                        .apply { start() }
                    ObjectAnimator.ofFloat(binding.chartFab, "translationY", -500f)
                        .apply { start() }
                    ObjectAnimator.ofFloat(binding.rankFab, "translationY", -750f).apply { start() }
                }
                isRotate = ViewAnimation.rotateFab(binding.mainFab, !isRotate)
            })

            trashList.observe(this@MainActivity, {
                addMarker(1)
            })

            trashCanList.observe(this@MainActivity, {
                addMarker(2)
            })
        }
    }

    private fun drawMap() {
        //check permission
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

        //mapView
        mapView = MapView(this)
        val mapViewContainer = binding.mapView
        mapViewContainer.addView(mapView)

        mapView.setMapCenterPoint(
            MapPoint.mapPointWithGeoCoord(
                location!!.latitude,
                location.longitude
            ), true
        )

    }

    private fun addMarker(num: Int) {
        when (num) {
            1 -> {
                val list = viewModel.trashList.value!!

                for (data in list) {
                    val trashMarker = MapPOIItem().apply {
                        itemName = data.area + data.address
                        mapPoint = MapPoint.mapPointWithGeoCoord(
                            data.latitude.toDouble(),
                            data.longitude.toDouble()
                        )
                        markerType = MapPOIItem.MarkerType.CustomImage
                        customImageResourceId = R.drawable.ic_trash
                        isCustomImageAutoscale = false
                        setCustomImageAnchor(0.5f, 1.0f)
                    }

                    mapView.addPOIItem(trashMarker)
                }
            }
            2 -> {
                val list = viewModel.trashCanList.value!!

                for (data in list) {
                    val trashCanMarker = MapPOIItem().apply {
                        itemName = data.area + data.address
                        mapPoint = MapPoint.mapPointWithGeoCoord(
                            data.latitude.toDouble(),
                            data.longitude.toDouble()
                        )
                        markerType = MapPOIItem.MarkerType.CustomImage
                        customImageResourceId = R.drawable.ic_trash_can
                        isCustomImageAutoscale = false
                        setCustomImageAnchor(0.5f, 1.0f)
                    }

                    mapView.addPOIItem(trashCanMarker)
                }
            }
        }
    }
}