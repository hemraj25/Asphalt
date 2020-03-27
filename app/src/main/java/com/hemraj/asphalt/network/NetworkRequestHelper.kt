package com.hemraj.asphalt.network

import android.net.Uri
import com.hemraj.asphalt.AsphaltApplication
import com.hemraj.asphalt.data.Request
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient

object NetworkRequestHelper {

    private val forceCacheInterceptor = Interceptor { chain ->
        val builder =  chain.request().newBuilder()
        if (!NetworkUtil.isInternetConnectionAvailable()) {
            builder.cacheControl(CacheControl.FORCE_CACHE);
        }
        chain.proceed(builder.build())
    }

    fun createRequest(request: Request): okhttp3.Request {
        val uri: Uri = checkNotNull(Uri.parse(request.url)) {
            "request.uri == null"
        }
        return okhttp3.Request.Builder().url(uri.toString()).build()
    }

    fun getOkHttpClient(): OkHttpClient {
        val cacheSize: Long = 10 * 1024 * 1024
        return OkHttpClient().newBuilder()
            .cache(Cache(AsphaltApplication.INSTANCE.cacheDir, cacheSize))
            .addInterceptor(forceCacheInterceptor)
            .build()
    }
}

