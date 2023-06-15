package com.chat.honey.extensions

import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.chat.honey.R
import com.chat.honey.util.extensions.dp2px

fun ImageView.loadImage(
    url: String?,
    round: Float = 12f,
    fallback: Int = R.drawable.ic_contacts,
    error: Int = R.drawable.ic_contacts,
) {
    Glide.with(this).load(url).fallback(fallback).error(error).apply(
        RequestOptions().transform(
            CenterCrop(), RoundedCorners(context.dp2px(round).toInt())
        )
    ).into(this)
}
fun ImageView.loadImage(
    url: Uri?,
    round: Float = 12f,
    fallback: Int = R.drawable.ic_contacts,
    error: Int = R.drawable.ic_contacts,
) {
    Glide.with(this).load(url).fallback(fallback).error(error).apply(
        RequestOptions().transform(
            CenterCrop(), RoundedCorners(context.dp2px(round).toInt())
        )
    ).into(this)
}