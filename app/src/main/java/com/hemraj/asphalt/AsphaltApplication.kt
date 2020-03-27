package com.hemraj.asphalt

import android.app.Application

class AsphaltApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

    companion object {
        lateinit var INSTANCE: AsphaltApplication
    }
}