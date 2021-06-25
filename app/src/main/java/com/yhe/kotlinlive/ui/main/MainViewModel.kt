package com.yhe.kotlinlive.ui.main

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.google.gson.JsonObject
import com.yhe.comm.log.LogUtils
import com.yhe.kotlinlive.data.main.StockRepository
import com.yhe.kotlinlive.data.main.StockRequestCallBack
import javax.inject.Inject

private val TAG = MainViewModel::class.simpleName!!
class MainViewModel(private var stockRepository: StockRepository) : ViewModel() {

    var stockCode = ObservableField<String>();

    fun queryStock(){
        stockCode.get()?.let {
            LogUtils.i(TAG, "queryStock: $it")
            stockRepository.getStock(it, object: StockRequestCallBack {
                override fun onResponse(jsonObject: JsonObject?) {
                    val body = jsonObject!!.get("body").toString()
                    LogUtils.i(TAG, "onResponse: $body")
                }

                override fun onFail(e: Throwable) {
                    e.printStackTrace()
                    LogUtils.e(TAG, "onFail: ${e.message}")
                }
            });
        }

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

class VModelFactory @Inject constructor(private var stockRepository: StockRepository): ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(p0: Class<T>): T {
        return MainViewModel(stockRepository) as T
    }

}

