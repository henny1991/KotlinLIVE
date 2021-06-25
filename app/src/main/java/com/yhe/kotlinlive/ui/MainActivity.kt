package com.yhe.kotlinlive.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.yhe.kotlinlive.R
import com.yhe.kotlinlive.ui.main.MainFragment
import com.yhe.comm.log.LogUtils
import com.yhe.kotlinlive.ui.live.LiveFragment

private val TAG = MainActivity::class.simpleName!!
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        LogUtils.i(TAG, "onCreate = $this >>> $savedInstanceState");
        //当savedInstanceState不为null时，说明Activity正在由系统重建(e.g. config change..)，这种情况下，Activity上的Fragment也会被一并按照销毁前FragmentManager的状态创建，
        // 所以不需要replace,replace会使用新的Fragment替换，导致状态丢失(Fragment的savedInstanceState无法使用，还有销毁前已经跳转到其他fragment的情况)
        /*if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }*/


    }

    fun onNext(view: View) {
        navController().navigate(R.id.screenRecorderFragment)
    }

    fun onBack(view: View) {
        navController().popBackStack()
    }

    fun onTest(view: View) {
        navController().navigate(R.id.liveFragment)
    }

    fun navController() = findNavController(R.id.fragmentContainerView)
}