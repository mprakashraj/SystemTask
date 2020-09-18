package com.example.systemtask.component

import androidx.databinding.DataBindingComponent
import com.example.systemtask.component.implemantation.IImageViewBinding
import com.example.systemtask.component.modules.BaseImageViewBinding


class BaseDataBindingComponent : DataBindingComponent {

    override fun getIImageViewBinding(): IImageViewBinding {
        return BaseImageViewBinding()
    }


}
