package com.hungps.timxebus.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by scit on 11/12/17.
 */

fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}