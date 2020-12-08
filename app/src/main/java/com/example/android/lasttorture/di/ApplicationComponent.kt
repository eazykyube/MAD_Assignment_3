package com.example.android.lasttorture.di

import com.example.android.lasttorture.view.MainActivity
import com.example.android.roomwordssample.di.SharedPrefsModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class, RoomModule::class, RepositoryModule::class, SharedPrefsModule::class])
interface ApplicationComponent {

    fun inject(activity: MainActivity)
}