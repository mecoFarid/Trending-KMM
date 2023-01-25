package com.mecofarid.trending.common.ui.widget

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import com.google.android.material.textview.MaterialTextView
import com.mecofarid.trending.android.R
import kotlin.math.max

private const val START_DRAWABLE = 0
private const val UNDEFINED_DRAWABLE_SIZE = -1

class IconizedTextView(context: Context, attrs: AttributeSet?) : MaterialTextView(context, attrs) {

  init {
    val styleSet = context.obtainStyledAttributes(attrs, R.styleable.IconizedTextView)
    updateDrawables(styleSet)
    styleSet.recycle()
  }

  private fun updateDrawables(styleSet: TypedArray) {
    val startDrawable = getModifiedDrawable(drawablePosition = START_DRAWABLE, styleSet)
    setCompoundDrawables(startDrawable, null, null, null)
  }

  private fun getModifiedDrawable(drawablePosition: Int, styleSet: TypedArray): Drawable? =
    compoundDrawables[drawablePosition]?.apply {
      val drawableSize = styleSet.getDimensionPixelSize(
        R.styleable.IconizedTextView_drawableStartSize,
        UNDEFINED_DRAWABLE_SIZE
      )
      val size = getScaledSize(drawableSize)
      setBounds(0, 0, size.width, size.height)
    }

  // We take defined drawable size as max size to scale the drawable's actual dimensions
  private fun Drawable.getScaledSize(drawableSize: Int): Size {
    val height = intrinsicHeight
    val width = intrinsicWidth

    if (drawableSize == UNDEFINED_DRAWABLE_SIZE)
      return Size(height, width)

    val ratio: Float = max(height, width).toFloat() / drawableSize
    return Size(height = (height / ratio).toInt(), width = (width / ratio).toInt())
  }

  private data class Size(val height: Int, val width: Int)
}
