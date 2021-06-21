package com.yhe.comm.log

import android.util.Log

object LogUtils {

    fun i(tag: String, msg: String){
        Log.i(LogContants.TAG, "[$tag] $msg");
    }

    fun e(tag: String, msg: String){
        Log.e(LogContants.TAG, "[$tag] $msg");
    }

}