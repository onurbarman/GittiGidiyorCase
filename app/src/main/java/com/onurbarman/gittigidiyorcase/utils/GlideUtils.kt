package com.onurbarman.gittigidiyorcase.utils

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.onurbarman.gittigidiyorcase.utils.ImageRequestListener

object GlideUtils {
    fun urlToImageView(context: Context, url: String, img: ImageView, width: Int, height: Int) {
        Glide.with(context)
            .load(Uri.parse(url))
            .override(width, height)
            .transition(DrawableTransitionOptions.withCrossFade())
            .transform(CenterCrop())
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .listener(ImageRequestListener())
            .into(img)
    }
}