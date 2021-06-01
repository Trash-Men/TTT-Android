package com.tjrwns8024.ttt_android.util

import android.content.Context
import android.graphics.Bitmap
import android.location.Address
import android.location.Geocoder
import android.os.Environment
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

fun bitmapToFile(bitmap: Bitmap, fileNameToSave: String): File? { // File name like "image.png"
    //create a file to write bitmap data
    var file: File? = null
    return try {
        val path = Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_PICTURES
        )

        file = File(path, "${fileNameToSave}.png")
        file.createNewFile()

        path.mkdirs()

        val bos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, bos)
        val bitmapdata = bos.toByteArray()

        val fos = FileOutputStream(file)
        fos.write(bitmapdata)
        fos.flush()
        fos.close()
        file
    } catch (e: Exception) {
        e.printStackTrace()
        file // it will return null
    }
}


fun getAddress(context: Context, lat: Float, long: Float): String {
    var nowAddress = "현재 위치를 확인 할 수 없습니다."
    val geocoder = Geocoder(context, Locale.KOREA)
    val address = mutableListOf<Address>()

    try {
        address += geocoder.getFromLocation(lat.toDouble(), long.toDouble(), 1)

        if (address.size > 0) {
            // 주소 받아오기
            val currentLocationAddress = address[0].adminArea.toString()
            nowAddress = currentLocationAddress
        }

    } catch (e: IOException) {
        e.printStackTrace()
    }

    return nowAddress
}