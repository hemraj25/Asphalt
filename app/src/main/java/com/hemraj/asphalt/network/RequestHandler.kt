package com.hemraj.asphalt.network

import android.graphics.Bitmap
import com.hemraj.asphalt.data.Request

interface RequestHandler {
    fun load(
        request: Request,
        callback: CallBack
    )


    interface CallBack {
        fun onSuccess(request: Request, bitmap: Bitmap)
        fun onError(e: Exception)
    }
}