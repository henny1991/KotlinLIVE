package com.yhe.kotlinlive.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yhe.comm.log.LogUtils
import com.yhe.kotlinlive.MyApplication
import com.yhe.kotlinlive.databinding.MainFragmentBinding
import javax.inject.Inject

private val TAG = MainFragment::class.simpleName!!
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    @Inject
    lateinit var VModelFactory: VModelFactory

    private lateinit var viewModel: MainViewModel

    lateinit var binding: MainFragmentBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtils.i(TAG, "onCreate $this");
        (activity?.applicationContext as MyApplication).component.mainComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = MainViewModel.instance(this, VModelFactory)
        binding.viewmodel = viewModel
        binding.query.setOnClickListener {
            viewModel.queryStock()
        }
    }

    override fun onResume() {
        super.onResume()
        LogUtils.i(TAG, "onResume");
    }

}