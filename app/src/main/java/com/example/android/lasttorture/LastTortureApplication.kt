package com.example.android.lasttorture

import android.app.Application
import com.example.android.lasttorture.di.ApplicationComponent
import com.example.android.lasttorture.di.DaggerApplicationComponent
import com.example.android.lasttorture.di.RoomModule


class LastTortureApplication : Application() {

    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        APPLICATION = this

        appComponent = DaggerApplicationComponent
            .builder().roomModule(RoomModule(this))
            .build()
    }

    companion object {
        lateinit var APPLICATION: Application
    }
}