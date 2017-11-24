package com.hungps.timxebus.activity.searchresult

import com.hungps.timxebus.basemvp.BaseMvpPresenter
import com.hungps.timxebus.model.UserRoute

/**
 * Created by scit on 11/12/17.
 */

class SearchResultPresenter() : BaseMvpPresenter<SearchResultContract.View>(), SearchResultContract.Presenter {
    var userRoutes = mutableListOf(
            UserRoute("Test", "Test", "Test", mutableListOf()),
            UserRoute("Test", "Test", "Test", mutableListOf()),
            UserRoute("Test", "Test", "Test", mutableListOf()),
            UserRoute("Test", "Test", "Test", mutableListOf()),
            UserRoute("Test", "Test", "Test", mutableListOf())
    )

    override fun getNewData() {
        mView?.setupUserRouteAdapter(userRoutes)
    }

}