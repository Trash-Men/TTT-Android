package com.tjrwns8024.data.util

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

fun File.toImageRequestBody(): RequestBody = this.asRequestBody(MultipartBody.FORM)