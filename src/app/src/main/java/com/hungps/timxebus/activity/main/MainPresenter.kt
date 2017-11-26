package com.hungps.timxebus.activity.main

import com.hungps.timxebus.basemvp.BaseMvpPresenter
import com.hungps.timxebus.model.Route

/**
 * Created by scit on 11/12/17.
 */

class MainPresenter() : BaseMvpPresenter<MainContract.View>(), MainContract.Presenter {
    var Routes = mutableListOf(
            Route("Test", emptyList()),
            Route("Test", emptyList()),
            Route("Test", emptyList()),
            Route("Test", emptyList()),
            Route("Test", emptyList()),
            Route("Test", emptyList()),
            Route("Test", emptyList())
    )

    override fun getNewData() {
        mView?.setupUserRouteAdapter(Routes)
    }

}