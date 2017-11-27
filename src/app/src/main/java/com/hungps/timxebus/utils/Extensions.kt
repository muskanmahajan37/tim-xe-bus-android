package com.hungps.timxebus.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
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

val View.isVisible: Boolean
    get() = visibility == View.VISIBLE

val JSONArray.lastIndex: Int
    get() = length() - 1

fun JSONArray.forEachJsonArray(action: (JSONArray) -> Unit): Unit {
    for (index in 0..this.lastIndex) action(this.getJSONArray(index))
}

val PASSED_ROUTE_DATA = "com.hungps.timxebus.utils.PASSED_ROUTE_DATA"

fun ListView.setListViewHeightBasedOnItems() {

    val listAdapter = adapter
    if (listAdapter != null) {
        val numberOfItems = listAdapter.count

        // Get total height of all items.
        var totalItemsHeight = 0
        for (itemPos in 0 until numberOfItems) {
            val item = listAdapter.getView(itemPos, null, this)
            item.measure(0, 0)
            totalItemsHeight += item.measuredHeight
        }

        // Get total height of all item dividers.
        val totalDividersHeight = dividerHeight * (numberOfItems - 1)

        // Set list height.
        val params = layoutParams
        params.height = totalItemsHeight + totalDividersHeight
        layoutParams = params
        requestLayout()
    }
}