package com.yhe.kotlinlive.ui.screenrecorder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yhe.comm.log.LogUtils
import com.yhe.kotlinlive.databinding.ScreenRecorderFragmentBinding
import javax.inject.Inject

val TAG = ScreenRecorderFragment::class.simpleName!!
class ScreenRecorderFragment: Fragment() {

    @Inject
    lateinit var Factory: ScreenRecorderFactory

    lateinit var binding: ScreenRecorderFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtils.i(TAG, "onCreate $this");
        DaggerScreenRecorderComponent.create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ScreenRecorderFragmentBinding.inflate(inflater, container, false);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val  viewModel = ScreenRecorderViewModel.instance(this, Factory)
        binding.viewmodel = viewModel
    }

    override fun onResume() {
        super.onResume()
        LogUtils.i(TAG, "onResume");
    }
}