package com.hungps.timxebus.activity.main

import android.app.Activity
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

    }

    interface Presenter : BaseMvpContract.Presenter<View> {

        fun getFavoriteRoutes(activity: Activity)

    }

}