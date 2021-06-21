package com.yhe.comm.di

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class GsonModule {

    @Singleton
    @Provides
    fun provideGson() = GsonBuilder()
        //.serializeNulls()
        .setLenient() //宽容模式，豁免一些json的语法错误
        .create()
}