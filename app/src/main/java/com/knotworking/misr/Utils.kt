package com.knotworking.misr

import android.content.Context
import android.support.annotation.ColorRes
import android.support.v4.graphics.drawable.DrawableCompat
import android.view.MenuItem


/**
 * Created by BRL on 12/08/17.
 */
object Utils {
    @JvmStatic
    fun tintMenuIcon(context: Context, item: MenuItem, @ColorRes color: Int) {
        val normalDrawable = item.icon
        val wrapDrawable = DrawableCompat.wrap(normalDrawable)
        DrawableCompat.setTint(wrapDrawable, context.resources.getColor(color))
        item.icon = wrapDrawable
    }
}