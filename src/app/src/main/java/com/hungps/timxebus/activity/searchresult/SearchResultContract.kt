package com.hungps.timxebus.activity.searchresult

import com.hungps.timxebus.basemvp.BaseMvpContract
import com.hungps.timxebus.model.UserRoute

/**
 * Created by scit on 11/12/17.
 */

interface SearchResultContract {

    interface View : BaseMvpContract.View {

        fun setupUserRouteAdapter(routes: MutableList<UserRoute>)

    }

    interface Presenter : BaseMvpContract.Presenter<View> {

        fun getNewData()

    }

}