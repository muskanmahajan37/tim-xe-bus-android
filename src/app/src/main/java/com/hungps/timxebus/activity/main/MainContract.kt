package com.hungps.timxebus.activity.main

import android.app.Activity
import com.hungps.timxebus.adapter.RouteAdapter
import com.hungps.timxebus.basemvp.BaseMvpContract
import com.hungps.timxebus.model.Block
import com.hungps.timxebus.model.Route

/*
* Author: scit
* Time: 11/12/17
*/

interface MainContract {

    interface View : BaseMvpContract.View {

        fun setupUserRouteAdapter(routes: MutableList<Route>)

        fun notifyItemRemoved(position: Int)

        fun showEmptyText(isShow: Boolean)

        fun notifyItemChanged(position: Int)

    }

    interface Presenter : BaseMvpContract.Presenter<View>, RouteAdapter.OnRouteListChange {

        fun getFavoriteRoutes(activity: Activity)

        fun setupDbHelper(activity: Activity)

    }

}