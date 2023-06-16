package com.baimsg.contact.extensions

import android.net.Uri
import android.widget.ImageView
import com.baimsg.contact.util.extensions.dp2px
import com.baimsg.resource.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

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

fun ImageView.loadImage(
    bytes: ByteArray?,
    round: Float = 12f,
    fallback: Int = R.drawable.ic_contacts,
    error: Int = R.drawable.ic_contacts,
) {
    Glide.with(this).load(bytes).fallback(fallback).error(error).apply(
        RequestOptions().transform(
            CenterCrop(), RoundedCorners(context.dp2px(round).toInt())
        )
    ).into(this)
}