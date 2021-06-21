package com.yhe.kotlinlive

import android.app.Application
import android.os.Process
import com.yhe.comm.log.LogUtils

private val TAG = MyApplication::class.simpleName!!;

class MyApplication : Application() {

    var component = DaggerAppComponent.create()

    override fun onCreate() {
        super.onCreate()
        LogUtils.i(TAG, "MyApplication onCreate: pid = ${Process.myPid()}")
    }
}