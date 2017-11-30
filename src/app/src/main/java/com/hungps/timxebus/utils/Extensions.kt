package com.hungps.timxebus.utils

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import org.json.JSONArray
import org.json.JSONObject
import android.net.NetworkInfo
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.support.v4.content.ContextCompat
import android.widget.ImageView
import com.hungps.timxebus.model.Block
import com.hungps.timxebus.model.BusBlock
import com.hungps.timxebus.model.Route
import com.hungps.timxebus.model.WalkBlock


/*
* Author: scit
* Time: 11/12/17
*/

fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

fun View.setVisible(visible: Boolean) {
    //this.visibility = if (visible) View.VISIBLE else View.GONE
    if (visible) {
        this.expand()
    } else {
        this.collapse()
    }
}

fun ImageView.setIconDrawable(activity: Activity, iconDrawable: Int) {
    this.setImageDrawable(ContextCompat.getDrawable(activity, iconDrawable))
}

val View.isVisible: Boolean
    get() = visibility == View.VISIBLE

val JSONArray.lastIndex: Int
    get() = length() - 1

fun JSONArray.forEachJsonArray(action: (JSONArray) -> Unit): Unit {
    for (index in 0..this.lastIndex) action(this.getJSONArray(index))
}

fun isNetworkConnected(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val activeNetwork = cm.activeNetworkInfo
    return activeNetwork != null && activeNetwork.isConnectedOrConnecting

}

fun MutableList<Route>.containsRoute(anotherRoute: Route): Boolean {
    this.forEach { currentRoute ->
        if (currentRoute.equals(anotherRoute)) {
            return true
        }
    }
    return false
}

fun MutableList<Block>.equalsWith(anotherRoute: MutableList<Block>): Boolean {
    if (this.size != anotherRoute.size) return false

    for (i in 0..(this.size - 1)) {
        if (
            (this[i] is BusBlock && anotherRoute[i] is WalkBlock)
            || (this[i] is WalkBlock && anotherRoute[i] is BusBlock)
            || !this[i].equals(anotherRoute[i])
        ) {
            return false
        }
    }

    return true
}

val PASSED_ROUTE_DATA = "com.hungps.timxebus.utils.PASSED_ROUTE_DATA"
