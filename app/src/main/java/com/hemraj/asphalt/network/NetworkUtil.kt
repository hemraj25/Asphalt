package com.hemraj.asphalt.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.core.content.ContextCompat.getSystemService
import com.hemraj.asphalt.AsphaltApplication


object NetworkUtil {

    fun isInternetConnectionAvailable(): Boolean {
        var result = false
        val connectivityManager = getSystemService<ConnectivityManager>(
            AsphaltApplication.INSTANCE, ConnectivityManager::class.java)
        connectivityManager?.let { cm ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                    result = when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                        else -> false
                    }
                }
            } else {
                result = cm.activeNetworkInfo?.isConnected?: false
            }
            return result
        }

        return result
    }
}