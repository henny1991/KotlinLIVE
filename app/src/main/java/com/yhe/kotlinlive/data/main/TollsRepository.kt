package com.yhe.kotlinlive.data.main

import com.google.gson.JsonObject
import com.yhe.kotlinlive.data.main.source.cloud.TollsCloudSource
import com.yhe.kotlinlive.ui.main.TollsRequestCallBack
import javax.inject.Inject

class TollsRepository @Inject constructor(private var tollsCloudSource: TollsCloudSource){

    fun getTolls(callback: TollsRequestCallBack) {
        tollsCloudSource.getTolls(callback)
    }

}