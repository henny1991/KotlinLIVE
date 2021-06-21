package com.yhe.kotlinlive

import com.yhe.comm.di.GsonModule
import com.yhe.comm.di.OkhttpModule
import com.yhe.kotlinlive.ui.live.LiveComponent
import com.yhe.kotlinlive.ui.main.MainComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [OkhttpModule::class, GsonModule::class, SubcomponentsModule::class])
interface AppComponent {

    fun mainComponent(): MainComponent.Factory

    fun liveComponent(): LiveComponent.Factory
}