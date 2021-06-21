package com.yhe.kotlinlive.ui.main

import com.yhe.comm.di.FragmentScope
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [MainModule::class])
interface MainComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }

    fun inject(fragment: MainFragment)
}