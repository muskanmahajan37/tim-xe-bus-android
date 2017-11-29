package com.hungps.timxebus.db

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hungps.timxebus.model.Route
import com.hungps.timxebus.utils.containsRoute

/*
* Author: scit
* Time: 11/29/17
*/

class DbHelper(val mContext: Context) {
    val PREFS_FILENAME = "com.hungps.timxebus.db.prefs"
    val KEY_ROUTES = "mRoutes"

    val mPrefs = mContext.getSharedPreferences(PREFS_FILENAME, 0);
    val mGson = Gson()

    var favoriteRoutes: MutableList<Route>
        get() = Gson().fromJson(
                    mPrefs.getString(KEY_ROUTES, "[]"),
                    object : TypeToken<MutableList<Route>>() {}.type
                )
        set(routes){ mPrefs.edit().putString(KEY_ROUTES, Gson().toJson(routes)).apply() }

    fun addRoute(route: Route) {
        if (favoriteRoutes.containsRoute(route)) return;

        val newRoutes = favoriteRoutes.toMutableList()
        newRoutes.add(route)

        favoriteRoutes = newRoutes
    }

    fun removeRoute(route: Route) {
        val newRoutes = favoriteRoutes.toMutableList()
        newRoutes.remove(route)

        favoriteRoutes = newRoutes
    }
}