package com.tjrwns8024.ttt_android.util

import android.R.attr
import android.graphics.Bitmap
import android.os.Environment
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

fun bitmapToFile(bitmap: Bitmap, fileNameToSave: String): File? { // File name like "image.png"
    //create a file to write bitmap data
    var file: File? = null
    return try {
        val path = Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_PICTURES
        )

        file  = File( path, "${fileNameToSave}.png")
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