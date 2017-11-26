package com.hungps.timxebus.activity.searchresult

import com.hungps.timxebus.basemvp.BaseMvpPresenter
import com.hungps.timxebus.model.Route

/**
 * Created by scit on 11/12/17.
 */

class SearchResultPresenter() : BaseMvpPresenter<SearchResultContract.View>(), SearchResultContract.Presenter {
    var userRoutes = mutableListOf(
            Route("Test", mutableListOf()),
            Route("Test", mutableListOf()),
            Route("Test", mutableListOf()),
            Route("Test", mutableListOf()),
            Route("Test", mutableListOf()),
            Route("Test", mutableListOf()),
            Route("Test", mutableListOf())
    )

    override fun getNewData() {
        mView?.setupUserRouteAdapter(userRoutes)
    }

}