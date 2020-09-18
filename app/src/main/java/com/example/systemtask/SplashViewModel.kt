package com.example.systemtask

import android.app.Application
import android.view.View
import androidx.databinding.ObservableField
import com.example.systemtask.baseActivity.BaseNavigator
import com.example.systemtask.baseActivity.BaseViewModel

class SplashViewModel(application: Application) : BaseViewModel<BaseNavigator>(application) {

    val progressBarVisible = ObservableField<Boolean>(false)

    val editTextUserName = ObservableField<String>("")
    val editTextPassword = ObservableField<String>("")

    fun onClickBtn(view: View) {
        getNavigator().onClickView(view)
    }

    fun validateFields(): Boolean {
        when {
            editTextUserName.get()!!.isEmpty() -> putToast("Please enter your userName")
            editTextPassword.get()!!.isEmpty() -> putToast("Please enter your password")
            editTextUserName.get() != "admin" && editTextPassword.get() != "admin123" -> putToast("Please enter valid userName and Password")
            else -> return true
        }
        return false
    }
}