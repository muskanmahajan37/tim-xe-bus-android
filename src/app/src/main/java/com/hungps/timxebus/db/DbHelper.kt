package com.hungps.timxebus.db

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hungps.timxebus.model.Route
import com.hungps.timxebus.utils.containsRoute
import com.google.gson.GsonBuilder
import com.hungps.timxebus.utils.BlockDeserializer


/*
* Author: scit
* Time: 11/29/17
*/

class DbHelper(val mContext: Context) {

    val PREFS_FILENAME = "com.hungps.timxebus.db.prefs"
    val KEY_ROUTES = "mRoutes"

    val mPrefs = mContext.getSharedPreferences(PREFS_FILENAME, 0);



    var favoriteRoutes: MutableList<Route>
        get() {
            val type = object : TypeToken<MutableList<Route>>() {}.type

            val gson = GsonBuilder()
                    .registerTypeAdapter(type, BlockDeserializer())
                    .create()

            return gson.fromJson(mPrefs.getString(KEY_ROUTES, "[]"), type)
        }
        set(routes){
            val json = Gson().toJson(routes)
            mPrefs.edit().putString(KEY_ROUTES, json).apply()
        }



    fun addRoute(route: Route) {
        if (favoriteRoutes.containsRoute(route)) return;

        val newRoutes = favoriteRoutes.toMutableList()
        newRoutes.add(route.copy(name = "Tuyến chưa đặt tên"))

        favoriteRoutes = newRoutes
    }



    fun removeRoute(route: Route) {
        val newRoutes = favoriteRoutes.toMutableList()
        newRoutes.remove(route)

        favoriteRoutes = newRoutes
    }

}