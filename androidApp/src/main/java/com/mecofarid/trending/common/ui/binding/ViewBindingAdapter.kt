package com.mecofarid.trending.common.ui.binding

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

@BindingAdapter("visible")
fun isVisible(view: View, visible: Boolean){
    view.isVisible = visible
}
