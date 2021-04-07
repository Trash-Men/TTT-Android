package com.tjrwns8024.ttt_android.ui

import android.Manifest
import android.animation.ObjectAnimator
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.tjrwns8024.ttt_android.R
import com.tjrwns8024.ttt_android.base.BaseActivity
import com.tjrwns8024.ttt_android.databinding.ActivityMainBinding
import com.tjrwns8024.ttt_android.util.ViewAnimation
import com.tjrwns8024.ttt_android.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import net.daum.mf.map.api.*

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(
    R.layout.activity_main,
    MainViewModel::class.java
) {
    override val viewModelStoreOwner = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        drawMap()
    }

    override fun observeEvent() {
        viewModel.isFabOpen.observe(this, {

            if (viewModel.isFabOpen.value!!) {
                ObjectAnimator.ofFloat(binding.pictureFab, "translationY", 0f).apply { start() }
                ObjectAnimator.ofFloat(binding.chartFab, "translationY", 0f).apply { start() }
                ObjectAnimator.ofFloat(binding.rankFab, "translationY", 0f).apply { start() }
            } else {
                ObjectAnimator.ofFloat(binding.pictureFab, "translationY", -250f).apply { start() }
                ObjectAnimator.ofFloat(binding.chartFab, "translationY", -500f).apply { start() }
                ObjectAnimator.ofFloat(binding.rankFab, "translationY", -750f).apply { start() }
            }
            viewModel.isRotate = ViewAnimation.rotateFab(binding.mainFab, !viewModel.isRotate)
        })
    }

    private fun drawMap() {
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
        val mapView = MapView(this)
        val mapViewContainer = binding.mapView
        mapViewContainer.addView(mapView)

        mapView.setMapCenterPoint(
            MapPoint.mapPointWithGeoCoord(
                location!!.latitude,
                location.longitude
            ), true
        )

        val marker = MapPOIItem()
        marker.itemName = "Default Marker"
        marker.tag = 0
        marker.mapPoint = MapPoint.mapPointWithGeoCoord(
            location.latitude,
            location.longitude
        )
        marker.markerType = MapPOIItem.MarkerType.BluePin // 기본으로 제공하는 BluePin 마커 모양.

        marker.selectedMarkerType =
            MapPOIItem.MarkerType.RedPin // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.

        mapView.addPOIItem(marker)
    }
}