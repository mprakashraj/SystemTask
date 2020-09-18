package com.example.systemtask

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.systemtask.baseActivity.BaseActivity
import com.example.systemtask.baseActivity.BaseNavigator
import com.example.systemtask.databinding.ActivityLoginBinding

class LoginActivity :  BaseActivity<ActivityLoginBinding, SplashViewModel>(), BaseNavigator {
    private lateinit var viewModel: SplashViewModel

    override fun getBindingVariable(): Int {
        return BR.loginVM
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun getViewModel(): SplashViewModel {
        viewModel = ViewModelProvider(this).get(SplashViewModel::class.java)
        return viewModel
    }

    override fun onClickView(v: View?) {
        when(v?.id){
            R.id.btn_login -> {
              if (viewModel.validateFields()){
                  val intent = Intent(this,MainActivity::class.java)
                  startActivity(intent)
              }
            }
        }
    }

    override fun goTo(clazz: Class<*>, mExtras: Bundle?) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNavigator(this)
    }
}