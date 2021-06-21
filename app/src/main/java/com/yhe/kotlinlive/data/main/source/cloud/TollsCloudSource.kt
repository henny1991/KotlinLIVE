package com.yhe.kotlinlive.data.main.source.cloud

import com.google.gson.JsonObject
import com.yhe.kotlinlive.ui.main.TollsRequestCallBack
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class TollsCloudSource @Inject constructor (private val retrofitService: TollsRetrofitService) {

    fun getTolls(callBack: TollsRequestCallBack){
        retrofitService.getTolls("v3-third-parties/telenav/tolls-list-4.89.json").enqueue(object : Callback<JsonObject>{
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                callBack.onResponse(response.body())
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                callBack.onFail(t)
            }

        })
    }


}