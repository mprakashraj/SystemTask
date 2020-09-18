package com.example.systemtask.baseActivity

import android.app.Application
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel

abstract class BaseViewModel<N>(application: Application) : AndroidViewModel(application) {

    private val mApplication: Application = application
    private var mNavigator: N? = null

    fun getNavigator(): N {
        return mNavigator!!
    }

    fun setNavigator(navigator: N) {
        this.mNavigator = navigator
    }

    fun putToast(message: String?) {
        Toast.makeText(mApplication, "" + message, Toast.LENGTH_SHORT).show()
    }
}