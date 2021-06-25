package com.yhe.kotlinlive.ui.screenrecorder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import javax.inject.Inject

class ScreenRecorderViewModel: ViewModel() {

    fun onStart(){}

    override fun onCleared() {
        super.onCleared()
    }

    companion object{
        fun instance(owner: ViewModelStoreOwner, factory: ViewModelProvider.Factory)
                = ViewModelProvider(owner, factory).get(ScreenRecorderViewModel::class.java)
    }

}

class ScreenRecorderFactory @Inject constructor(): ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(p0: Class<T>): T
        = ScreenRecorderViewModel() as T

}