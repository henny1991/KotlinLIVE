package com.yhe.kotlinlive.ui.live

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yhe.comm.log.LogUtils
import com.yhe.kotlinlive.MyApplication
import com.yhe.kotlinlive.databinding.LiveFragmentBinding
import okhttp3.OkHttpClient
import okhttp3.Request
import javax.inject.Inject

val TAG = LiveFragment::class.simpleName!!
class LiveFragment: Fragment() {

    @Inject
    lateinit var factory: LiveViewModelFactory

    @Inject
    lateinit var okhttp: OkHttpClient

    lateinit var binding: LiveFragmentBinding

    lateinit var viewModel: LiveViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtils.i(TAG, "onCreate $this");
        (activity?.applicationContext as MyApplication).component.liveComponent().create().inject(this)
        LogUtils.i(TAG, "okhttp1 = $okhttp")

        //test code
        Thread(Runnable {
            try {
                val request = Request.Builder()
                    .url("https://denalikr.telenav.com/entity/v4/search/json?location=37.518061543337566%2C126.7189091346687&unification=true&match_type=fuzzy&address_line=2&f.cat.list=771&limit=50&sort=best_match&user_id=dummy_user&locale=ko-KR&linkage_mode=0")
                    .get().build()
                val call = okhttp.newCall(request)
                val response = call.execute()
                LogUtils.i("TEST", response.body()!!.string())
            } catch (e: Exception) {
                LogUtils.e("TEST", "okhttp exception: ", e)
            }
        }).start()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LiveFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = LiveViewModel.instance(this, factory)
        binding.viewmodel = viewModel
    }

    companion object {
        fun newInstance(args: Bundle?) : LiveFragment {
            val fragment = LiveFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onResume() {
        super.onResume()
        LogUtils.i(TAG, "onResume");
    }

}