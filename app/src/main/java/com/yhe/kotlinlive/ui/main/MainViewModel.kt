package com.yhe.kotlinlive.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.google.gson.JsonObject
import com.yhe.comm.log.LogUtils
import com.yhe.kotlinlive.data.main.TollsRepository
import javax.inject.Inject

private val TAG = MainViewModel::class.simpleName!!
class MainViewModel(private var tollsRepository: TollsRepository) : ViewModel() {

    fun loadToll(){
        LogUtils.i(TAG, "loadToll")
        tollsRepository.getTolls(object: TollsRequestCallBack{
            override fun onResponse(jsonObject: JsonObject?) {
                LogUtils.i(TAG, "onResponse: $jsonObject")
            }

            override fun onFail(e: Throwable) {
                LogUtils.e(TAG, "onResponse: ${e.message}")
            }
        });
    }

    override fun onCleared() {
        super.onCleared()
        LogUtils.i(TAG, "onCleared")
    }

    companion object{
        fun instance(owner: ViewModelStoreOwner, VModelFactory: VModelFactory):MainViewModel
            = ViewModelProvider(owner, VModelFactory).get(MainViewModel::class.java)
    }
}

class VModelFactory @Inject constructor(private var tollsRepository: TollsRepository): ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(p0: Class<T>): T {
        return MainViewModel(tollsRepository) as T
    }

}

interface TollsRequestCallBack{

    fun onResponse(jsonObject: JsonObject?)

    fun onFail(e: Throwable)
}