package com.hungps.timxebus.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by scit on 11/12/17.
 */

fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

fun View.setVisible(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}

val JSONArray.lastIndex: Int
    get() = length() - 1

fun JSONArray.forEachJsonArray(action: (JSONArray) -> Unit): Unit {
    for (index in 0..this.lastIndex) action(this.getJSONArray(index))
}