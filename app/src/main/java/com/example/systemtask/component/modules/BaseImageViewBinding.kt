package com.example.systemtask.component.modules

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.systemtask.component.implemantation.IImageViewBinding
import com.example.systemtask.R

class BaseImageViewBinding : IImageViewBinding {

    override fun setImageFromUrl(imageView: ImageView, filePath: String?) {
        val option: RequestOptions = RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .placeholder(R.drawable.loginbackground)
                .fitCenter()

        Glide.with(imageView.context)
                .load(filePath)
                .apply(option)
                .into(imageView)
    }
}
