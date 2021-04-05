package com.tjrwns8024.ttt_android.ui

import android.Manifest
import android.animation.ObjectAnimator
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.tjrwns8024.ttt_android.R
import com.tjrwns8024.ttt_android.base.BaseActivity
import com.tjrwns8024.ttt_android.databinding.ActivityMainBinding
import com.tjrwns8024.ttt_android.util.ViewAnimation
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
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

    fun clickRankActivity(view: View) {
        start<RankActivity>()
    }

    fun clickChartActivity(view: View) {
        start<ChartActivity>()
    }

    fun toggleFab(view: View) {
        isRotate = ViewAnimation.rotateFab(binding.mainFab, !isRotate)
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

}