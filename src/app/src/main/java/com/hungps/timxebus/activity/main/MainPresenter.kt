package com.hungps.timxebus.activity.main

import android.app.Activity
import com.hungps.timxebus.basemvp.BaseMvpPresenter
import com.hungps.timxebus.db.DbHelper
import com.hungps.timxebus.model.Block
import com.hungps.timxebus.model.Route

/*
* Author: scit
* Time: 11/12/17
*/

class MainPresenter() : BaseMvpPresenter<MainContract.View>(), MainContract.Presenter {

    lateinit var mRoutes: MutableList<Route>
    lateinit var mDbHelper: DbHelper

    override fun getFavoriteRoutes(activity: Activity) {
        mDbHelper = DbHelper(activity)
        mRoutes = mDbHelper.favoriteRoutes
        mView?.setupUserRouteAdapter(mRoutes)
    }

}