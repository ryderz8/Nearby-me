package com.app.nearby_me.helper

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.app.nearby_me.R


@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String) =
    Glide.with(view.context).load(url).error(R.drawable.ic_placeholder).into(view)
