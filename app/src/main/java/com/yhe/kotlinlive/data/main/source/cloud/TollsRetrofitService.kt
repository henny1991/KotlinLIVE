package com.yhe.kotlinlive.data.main.source.cloud

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TollsRetrofitService {

    @GET("{tolls}")
    fun getTolls(@Path("tolls") tolls: String): Call<JsonObject>

}