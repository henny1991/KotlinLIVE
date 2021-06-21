package com.yhe.kotlinlive

import com.yhe.kotlinlive.ui.live.LiveComponent
import com.yhe.kotlinlive.ui.main.MainComponent
import dagger.Module

@Module(subcomponents = [MainComponent::class, LiveComponent::class])
class SubcomponentsModule {
}