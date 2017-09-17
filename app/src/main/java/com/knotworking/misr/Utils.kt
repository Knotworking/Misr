package com.knotworking.misr

import android.content.Context
import android.support.annotation.ColorRes
import android.support.v4.graphics.drawable.DrawableCompat
import android.view.MenuItem
import com.knotworking.misr.Constants.MILLIS_IN_SECOND
import com.knotworking.misr.Constants.MINUTES_IN_HOUR
import com.knotworking.misr.Constants.SECONDS_IN_MINUTE
import com.knotworking.misr.Constants.WORKING_HOURS_IN_MONTH


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

    fun payPerHour(salary: Float) = salary / WORKING_HOURS_IN_MONTH

    fun hoursFromMillis(millis: Long): Float = millis.toFloat() / MILLIS_IN_SECOND / SECONDS_IN_MINUTE / MINUTES_IN_HOUR

    fun hoursFromSeconds(seconds: Int): Float = seconds.toFloat() / SECONDS_IN_MINUTE / MINUTES_IN_HOUR

    fun hoursFromMinutes(minutes: Int): Float = minutes.toFloat() / MINUTES_IN_HOUR
}