package com.yhe.kotlinlive.ui.live

import com.yhe.comm.di.FragmentScope
import com.yhe.comm.di.OkhttpModule
import com.yhe.kotlinlive.ui.main.MainComponent
import dagger.Subcomponent

@FragmentScope
@Subcomponent
interface LiveComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): LiveComponent
    }

    fun inject(liveFragment: LiveFragment)

}