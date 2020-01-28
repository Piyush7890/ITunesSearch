package com.piyush.itunessearch

import android.app.Application
import com.piyush.itunessearch.injection.AppComponent
import com.piyush.itunessearch.injection.DaggerAppComponent

class ITunesSearch : Application()
{
    lateinit var appComponent : AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().build()
    }
}