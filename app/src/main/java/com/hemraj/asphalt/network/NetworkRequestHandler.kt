package com.hemraj.asphalt.network

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.hemraj.asphalt.data.Request
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

class NetworkRequestHandler(private val callFactory: Call.Factory) : RequestHandler {

    override fun load(request: Request, callback: RequestHandler.CallBack) {
        val callRequest: okhttp3.Request = NetworkRequestHelper.createRequest(request)
        callFactory.newCall(callRequest).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                if (!response.isSuccessful) {
                    Log.d("NetworkRequestHandler", "response is unsuccessful")
                    callback.onError(Exception("something went wrong"))
                }

                Log.d("NetworkRequestHandler", "response successful")
                try {
                    val bitmap: Bitmap = BitmapFactory.decodeStream(response.body!!.byteStream())
                    Log.d("NRH bitmap size", bitmap.byteCount.toString())
                    callback.onSuccess(request, bitmap)
                } catch (e: Exception) {
                    Log.d("NetworkRequestHandler", e.message ?: "Exception")
                    response.body!!.close()
                    callback.onError(e)
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                Log.d("NetworkRequestHandler", e.message ?: "IOException")
                callback.onError(e)
            }
        })
    }

}