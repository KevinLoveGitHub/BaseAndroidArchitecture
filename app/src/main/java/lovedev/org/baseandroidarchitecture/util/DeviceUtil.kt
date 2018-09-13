package com.shine.syncclassroom.util

import android.annotation.SuppressLint
import android.content.Context
import android.net.wifi.WifiManager
import android.os.Build
import java.net.InetAddress
import java.net.NetworkInterface
import java.net.SocketException


/**
 * @author Kevin
 * @data 2018/8/9
 */
object DeviceUtil {
    const val TAG = "DeviceUtil"

    /**
     * 获取移动设备本地IP
     * @return
     */
    private fun getLocalInetAddress(): InetAddress? {
        var ip: InetAddress? = null
        try {
            //列举
            val en_netInterface = NetworkInterface.getNetworkInterfaces()
            while (en_netInterface.hasMoreElements()) {//是否还有元素
                val ni = en_netInterface.nextElement() as NetworkInterface//得到下一个元素
                val en_ip = ni.inetAddresses//得到一个ip地址的列举
                while (en_ip.hasMoreElements()) {
                    ip = en_ip.nextElement()
                    if (!ip!!.isLoopbackAddress && ip.hostAddress.indexOf(":") == -1)
                        break
                    else
                        ip = null
                }

                if (ip != null) {
                    break
                }
            }
        } catch (e: SocketException) {

            e.printStackTrace()
        }

        return ip
    }

    /**
     * 获取本地IP
     * @return
     */
    fun getLocalIpAddress(): String? {
        try {
            val en = NetworkInterface
                    .getNetworkInterfaces()
            while (en.hasMoreElements()) {
                val intf = en.nextElement()
                val enumIpAddr = intf
                        .inetAddresses
                while (enumIpAddr.hasMoreElements()) {
                    val inetAddress = enumIpAddr.nextElement()
                    if (!inetAddress.isLoopbackAddress) {
                        return inetAddress.hostAddress.toString()
                    }
                }
            }
        } catch (ex: SocketException) {
            ex.printStackTrace()
        }

        return null
    }

    @SuppressLint("HardwareIds")
    fun getLocalMacAddress(context: Context): String? {
        val mac: String?
        mac = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getMachineHardwareAddress()
        } else {
            val wifi = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
            val info = wifi.connectionInfo
            info.macAddress.replace(":", "")
        }

        return mac
    }

    private fun getMachineHardwareAddress(): String? {
        val interfaceAddress = NetworkInterface.getNetworkInterfaces()
        var hardWareAddress: String? = null
        var iF: NetworkInterface? = null
        while (interfaceAddress.hasMoreElements()) {
            iF = interfaceAddress.nextElement()
            try {
                hardWareAddress = bytesToString(iF!!.hardwareAddress)
                if (hardWareAddress == null) continue
            } catch (e: SocketException) {
                e.printStackTrace()
            }

        }
        if (iF != null && iF.name == "wlan0") {
            hardWareAddress = hardWareAddress!!.replace(":", "")
        }
        return hardWareAddress
    }

    /***
     * byte转为String
     * @param bytes
     * @return
     */
    private fun bytesToString(bytes: ByteArray?): String? {
        if (bytes == null || bytes.isEmpty()) {
            return null
        }
        val buf = StringBuilder()
        for (b in bytes) {
            buf.append(String.format("%02X:", b))
        }
        if (buf.isNotEmpty()) {
            buf.deleteCharAt(buf.length - 1)
        }
        return buf.toString()
    }
}