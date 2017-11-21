package com.hungps.timxebus.activity.main

import com.hungps.timxebus.basemvp.BaseMvpPresenter
import com.hungps.timxebus.model.UserRoute

/**
 * Created by scit on 11/12/17.
 */

class MainPresenter() : BaseMvpPresenter<MainContract.View>(), MainContract.Presenter {
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