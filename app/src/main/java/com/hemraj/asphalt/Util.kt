package com.hemraj.asphalt

import android.graphics.Bitmap
import android.util.LruCache

private val maxCacheSize: Int = (Runtime.getRuntime().maxMemory() / 1024).toInt() / 8

object Util {

    var memoryCache: LruCache<String, Bitmap>? = null

    fun getLruCache(): LruCache<String, Bitmap> {
        return memoryCache?: object : LruCache<String, Bitmap>(maxCacheSize) {
            override fun sizeOf(key: String, bitmap: Bitmap): Int {
                return bitmap.byteCount / 1024
            }
        }.also {
            memoryCache = it
        }
    }
}