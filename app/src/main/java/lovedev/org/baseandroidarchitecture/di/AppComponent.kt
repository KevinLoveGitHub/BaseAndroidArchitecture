package com.shine.syncclassroom.di

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import lovedev.org.baseandroidarchitecture.App
import javax.inject.Singleton

/**
 * @author Kevin
 * @data 2018/8/27
 */
@Singleton
@Component(modules = [
    AppModule::class,
    ApiModule::class,
    ActivityBuilder::class,
    AndroidSupportInjectionModule::class])
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()
}