package com.hungps.timxebus.activity.searchresult

import com.hungps.timxebus.basemvp.BaseMvpPresenter
import com.hungps.timxebus.model.Block
import com.hungps.timxebus.model.Route

/*
* Author: scit
* Time: 11/12/17
*/

class SearchResultPresenter() : BaseMvpPresenter<SearchResultContract.View>(), SearchResultContract.Presenter {
    var userRoutes = mutableListOf(
            Block("Test", emptyArray()),
            Block("Test", emptyArray()),
            Block("Test", emptyArray()),
            Block("Test", emptyArray()),
            Block("Test", emptyArray()),
            Block("Test", emptyArray()),
            Block("Test", emptyArray())
    )

    override fun setupRecyckerView(routes: MutableList<Route>) {
        mView?.setupUserRouteAdapter(routes)
    }

}