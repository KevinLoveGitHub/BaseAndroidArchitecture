package com.shine.syncclassroom.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import lovedev.org.baseandroidarchitecture.App

/**
 * @author Kevin
 * @data 2018/8/27
 */
@Module
abstract class AppModule {

    @Binds
    abstract fun bindContext(application: App): Context

    @Binds
    abstract fun bindApplication(application: App): Application
}