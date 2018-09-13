package lovedev.org.baseandroidarchitecture

import com.shine.syncclassroom.di.DaggerAppComponent
import com.shine.syncclassroom.singleton.DeviceInfo
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import org.lovedev.util.Utils

/**
 * @author Kevin
 * @data 2018/8/9
 */
class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }

    override fun onCreate() {
        super.onCreate()
        Utils.init(this)
        DeviceInfo.init(this)
    }
}