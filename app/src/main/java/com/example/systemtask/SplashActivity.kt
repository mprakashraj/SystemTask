package com.example.systemtask

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.systemtask.baseActivity.BaseActivity
import com.example.systemtask.baseActivity.BaseNavigator
import com.example.systemtask.databinding.ActivitySplashBinding

class SplashActivity :  BaseActivity<ActivitySplashBinding, SplashViewModel>(), BaseNavigator {
    private lateinit var viewModel: SplashViewModel

    override fun getBindingVariable(): Int {
        return BR.splashVM
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun getViewModel(): SplashViewModel {
        viewModel = ViewModelProvider(this).get(SplashViewModel::class.java)
        return viewModel
    }

    override fun onClickView(v: View?) {

    }

    override fun goTo(clazz: Class<*>, mExtras: Bundle?) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNavigator(this)
        val splashTimeOut : Long=3000 // 3 sec

        // 5 seconds wait for same activity then it will automatically go for homePage

        Handler().postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, splashTimeOut)
    }
}