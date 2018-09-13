package com.shine.syncclassroom.singleton

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import com.shine.syncclassroom.util.DeviceUtil
import io.reactivex.Flowable
import org.lovedev.util.TimeUtils
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * @author Kevin
 * @data 2018/8/9
 */
object DeviceInfo {

    const val TAG = "DeviceInfo"
    lateinit var clientIp: String
    lateinit var clientId: String
    var systemTime: MutableLiveData<Long> = MutableLiveData()
    var formatSystemTime: MutableLiveData<String> = MutableLiveData()

    fun init(context: Context) {
        clientIp = DeviceUtil.getLocalIpAddress()!!
        clientId = DeviceUtil.getLocalMacAddress(context)!!
    }

    fun setSystemTime(time: Long) {
        val timeFormat = SimpleDateFormat("yyyy年MM月dd HH:mm", Locale.getDefault())
        Flowable.interval(0, 1, TimeUnit.MINUTES)
                .subscribe {
                    systemTime.postValue(time + it * 60 * 1000)
                    formatSystemTime.postValue(TimeUtils.millis2String(time + it * 60 * 1000, timeFormat))
                }
    }
}
