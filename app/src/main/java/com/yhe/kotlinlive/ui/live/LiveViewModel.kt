package com.yhe.kotlinlive.ui.live

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import javax.inject.Inject

class LiveViewModel: ViewModel() {

    fun onStart(){}

    companion object{
        fun instance(owner: ViewModelStoreOwner, factory: ViewModelProvider.Factory)
        = ViewModelProvider(owner, factory).get(LiveViewModel::class.java)
    }
}

class LiveViewModelFactory @Inject constructor(): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(p0: Class<T>): T {
        return LiveViewModel() as T
    }

}