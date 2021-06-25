package com.yhe.kotlinlive.ui.screenrecorder

import dagger.Component

@Component
interface ScreenRecorderComponent {

    fun inject(fragment: ScreenRecorderFragment)
}