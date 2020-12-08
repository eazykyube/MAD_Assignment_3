package com.example.android.lasttorture.di

import android.content.Context
import com.example.android.lasttorture.LastTortureApplication
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    fun provideApplication() = LastTortureApplication.APPLICATION

    @Provides
    fun provideContext(): Context = LastTortureApplication.APPLICATION

}