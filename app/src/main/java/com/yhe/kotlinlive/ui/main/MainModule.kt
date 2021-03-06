package com.yhe.kotlinlive.ui.main

import com.yhe.kotlinlive.data.main.source.cloud.TollsRetrofitService
import com.yhe.kotlinlive.util.Contants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class MainModule {

    @Provides
    fun provideTollsRetrofitService(client: OkHttpClient): TollsRetrofitService {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(Contants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TollsRetrofitService::class.java)
    }
}