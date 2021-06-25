package com.yhe.kotlinlive.data.main

import com.google.gson.JsonObject
import com.yhe.kotlinlive.data.main.source.cloud.StockCloudSource
import javax.inject.Inject

class StockRepository @Inject constructor(private var stockCloudSource: StockCloudSource){

    fun getStock(stockCode: String, callback: StockRequestCallBack) {
        stockCloudSource.getStock(stockCode, callback)
    }

}

interface StockRequestCallBack{

    fun onResponse(jsonObject: JsonObject?)

    fun onFail(e: Throwable)
}