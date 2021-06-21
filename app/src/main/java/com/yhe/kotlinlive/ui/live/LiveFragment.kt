package com.yhe.kotlinlive.ui.live

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yhe.kotlinlive.MyApplication
import com.yhe.kotlinlive.R
import okhttp3.OkHttpClient
import javax.inject.Inject

class LiveFragment: Fragment() {

    @Inject
    lateinit var okhttp: OkHttpClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.applicationContext as MyApplication).component.liveComponent().create().inject(this)
        Log.i("xixixi", "okhttp1 = $okhttp")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.live_fragment, container, false)
    }

    companion object {
        fun newInstance(args: Bundle?) : LiveFragment {
            val fragment = LiveFragment()
            fragment.arguments = args
            return fragment
        }
    }

}