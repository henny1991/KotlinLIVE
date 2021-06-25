package com.yhe.kotlinlive.data.main.source.cloud

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StockRetrofitService {

    //http://hq.sinajs.cn/list=sz002307,sh600928
    @GET("/")
    fun getStock(@Query("list") list: String): Call<JsonObject>

}