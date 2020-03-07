package com.chetan.movietime.common

import android.graphics.Color
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.BitmapImageViewTarget

/**
 * Created by Chetan on 2020-03-05.
 */
const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/original"

@BindingAdapter("set_picture")
fun setPicture(imageView: ImageView, imageUrl: String?) {
    val circularProgressDrawable = CircularProgressDrawable(imageView.context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.setColorSchemeColors(Color.WHITE)
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()

    GlideApp
        .with(imageView.context)
        .asBitmap()
        .load(IMAGE_BASE_URL + imageUrl)
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .placeholder(circularProgressDrawable)
        .fitCenter()
        .centerCrop()
        .dontAnimate()
        .encodeQuality(50)
        .into(object : BitmapImageViewTarget(imageView) {
        })
}

@BindingAdapter(value = ["vote_average", "vote_count"])
fun setVotes(textView: TextView, voteAverage: Double, voteCount: Int) {
    textView.text = "${voteAverage * 10}% ($voteCount votes)"
}