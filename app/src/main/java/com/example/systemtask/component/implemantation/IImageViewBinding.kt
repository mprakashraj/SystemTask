package com.example.systemtask.component.implemantation

import android.widget.ImageView
import androidx.databinding.BindingAdapter

interface IImageViewBinding {

    @BindingAdapter("customImageFromUrl")
    fun setImageFromUrl(imageView: ImageView, filePath: String?)
}
