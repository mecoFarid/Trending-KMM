package com.mecofarid.trending.common.ui.binding

import androidx.annotation.DrawableRes
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.mecofarid.trending.android.R

@BindingAdapter(value = ["dividerOrientation", "dividerDrawable"], requireAll = false)
fun setDividerDecoration(view: RecyclerView, dividerOrientation: Int?, @DrawableRes dividerDrawableResId: Int?){
    val context = view.context
    val orientation = dividerOrientation ?: DividerItemDecoration.VERTICAL
    val drawableResId = dividerDrawableResId ?: R.drawable.divider_bg
    val divider = DividerItemDecoration(context, orientation)
    val drawable = ResourcesCompat.getDrawable(context.resources, drawableResId, context.theme)!!
    divider.setDrawable(drawable)
    view.addItemDecoration(divider)
}
