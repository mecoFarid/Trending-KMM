package com.mecofarid.trending.common.ui.binding

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.mecofarid.trending.android.R

@BindingAdapter(value = ["imageUrl", "placeholderResId"], requireAll = false)
fun setImageUrl(view: ImageView, url: String?, @DrawableRes placeholder: Int?){
    val placeholderResId = placeholder ?: R.drawable.circle_placeholder_bg
    Glide.with(view)
        .load(url)
        .placeholder(placeholderResId)
        .circleCrop()
        .into(view)
}
