package com.yhe.kotlinlive.data.main.source.cloud

import com.google.gson.JsonObject
import com.yhe.kotlinlive.data.main.StockRequestCallBack
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class StockCloudSource @Inject constructor (private val retrofitService: StockRetrofitService) {

    fun getStock(stockCode: String, callBack: StockRequestCallBack){
        retrofitService.getStock(stockCode).enqueue(object : Callback<JsonObject>{

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                callBack.onResponse(response.body())
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                callBack.onFail(t)
            }

        })
    }


}