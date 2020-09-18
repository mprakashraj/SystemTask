package com.example.systemtask

import android.app.Application
import androidx.databinding.DataBindingUtil
import com.example.systemtask.component.BaseDataBindingComponent

class SystemTaskApplication  : Application() {
    companion object{
        private var mInstance : SystemTaskApplication? = null

        fun getInstance() : SystemTaskApplication? {
            return mInstance
        }
    }

    override fun onCreate() {
        super.onCreate()
        mInstance = this
        DataBindingUtil.setDefaultComponent(BaseDataBindingComponent())
    }
}