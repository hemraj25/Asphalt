package com.hemraj.asphalt.network

import android.graphics.Bitmap
import android.os.Handler
import android.util.Log
import android.util.LruCache
import androidx.core.content.ContextCompat
import com.hemraj.asphalt.AsphaltApplication
import com.hemraj.asphalt.BitmapUtil
import com.hemraj.asphalt.R
import com.hemraj.asphalt.data.Request

class BitmapHunter(val request: Request, val memoryCache: LruCache<String, Bitmap>) {
    var handler: Handler = Handler()

    @Synchronized
    fun hunt() {
        val bitmap = checkImageInCache(request.url)

        bitmap?.let {
            Log.d("BitmapHunter","from cache")
            request.imageView.setImageBitmap(bitmap)
        }?: run {
            Log.d("BitmapHunter","from url")
            NetworkRequestHandler(NetworkRequestHelper.getOkHttpClient()).load(
                request, object : RequestHandler.CallBack {
                    override fun onSuccess(request: Request, bitmap: Bitmap) {
                        val scaledBitmap = BitmapUtil.getScaleBitmapForLoad(bitmap, request.imageView.measuredWidth, request.imageView.measuredHeight)
                        handler.post {
                            scaledBitmap?.let {
                                Log.d("BitmapHunter","putting ${request.url} to cache")
                                memoryCache.put(request.url, it)
                                request.imageView.setImageBitmap(it)
                            }

                        }
                    }

                    override fun onError(e: Exception) {
                        Log.d("BitmapHunter onError",e.message?: "exception")
                        handler.post {
                            request.imageView.setImageDrawable(
                                ContextCompat.getDrawable(
                                    AsphaltApplication.INSTANCE,
                                    R.drawable.error
                                )
                            )
                        }
                    }
                })
        }
    }

    @Synchronized
    private fun checkImageInCache(imageUrl: String): Bitmap? = memoryCache.get(imageUrl)

}