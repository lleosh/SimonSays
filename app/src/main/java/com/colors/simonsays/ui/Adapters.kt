package com.colors.simonsays.ui

import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

import androidx.databinding.BindingAdapter


@BindingAdapter("background_color")
fun RelativeLayout.setColor(@ColorRes colorId: Int) {
    if (colorId == 0) return
    setBackgroundColor(ContextCompat.getColor(context, colorId))
}

@BindingAdapter("text_color")
fun TextView.setColor(@ColorRes colorId: Int) {
    if (colorId == 0) return
    setTextColor(ContextCompat.getColor(context, colorId))
}