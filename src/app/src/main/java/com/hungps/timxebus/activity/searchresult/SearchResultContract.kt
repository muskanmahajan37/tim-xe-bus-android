package com.hungps.timxebus.activity.searchresult

import android.content.Context
import com.hungps.timxebus.adapter.RouteAdapter
import com.hungps.timxebus.basemvp.BaseMvpContract
import com.hungps.timxebus.db.DbHelper
import com.hungps.timxebus.model.Block
import com.hungps.timxebus.model.Route

/**
 * Created by scit on 11/12/17.
 */

interface SearchResultContract {

    interface View : BaseMvpContract.View {

        fun setupUserRouteAdapter(routes: MutableList<Route>, dbHelper: DbHelper)

    }

    interface Presenter : BaseMvpContract.Presenter<View>, RouteAdapter.OnRouteListChange {

        fun setupRecyclerView(context: Context, routes: MutableList<Route>)

    }

}