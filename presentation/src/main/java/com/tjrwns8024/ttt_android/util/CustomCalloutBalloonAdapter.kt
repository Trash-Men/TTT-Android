package com.tjrwns8024.ttt_android.util

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.tjrwns8024.ttt_android.R
import com.tjrwns8024.ttt_android.ui.MainActivity
import net.daum.mf.map.api.CalloutBalloonAdapter
import net.daum.mf.map.api.MapPOIItem
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.net.UnknownHostException


class CustomCalloutBalloonAdapter(inflater: LayoutInflater) :
    CalloutBalloonAdapter {
    private val mCalloutBalloon: View = inflater.inflate(R.layout.balloon_layout, null)

    override fun getCalloutBalloon(poiItem: MapPOIItem): View {
        val item = MainActivity.mapHash[poiItem.tag]

        (mCalloutBalloon.findViewById(R.id.ball_image) as ImageView).setImageDrawable(createDrawableFromUrl(item!!.photoURL))
        (mCalloutBalloon.findViewById(R.id.ball_tv_name) as TextView).text = item.area
        (mCalloutBalloon.findViewById(R.id.ball_tv_address) as TextView).text = item.createdAt.substring(0,10)

        return mCalloutBalloon
    }

    override fun getPressedCalloutBalloon(poiItem: MapPOIItem): View? {
        return null
    }

    private fun createDrawableFromUrl(url: String): Drawable? {
        return try {
            val inputStream :InputStream = this.fetch(url) as InputStream

            Drawable.createFromStream ( inputStream, "src")
        } catch ( e: MalformedURLException) {
            e.printStackTrace()
            null
        } catch ( e:IOException) {
            e.printStackTrace()
            null
        }
    }

    private fun fetch(address: String): Any?{
        return try {
            val url = URL("https://ttt-image.s3.ap-northeast-2.amazonaws.com/$address")
            val conn = url.openConnection() as HttpURLConnection
            conn.responseCode
            url.content
        }catch ( e:UnknownHostException){
            e.printStackTrace()
            null
        }
    }

}