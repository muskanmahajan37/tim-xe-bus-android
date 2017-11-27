package com.hungps.timxebus.activity.main

import com.hungps.timxebus.basemvp.BaseMvpPresenter
import com.hungps.timxebus.model.Block

/**
 * Created by scit on 11/12/17.
 */

class MainPresenter() : BaseMvpPresenter<MainContract.View>(), MainContract.Presenter {
    var Routes = mutableListOf(
            Block("Test", emptyArray()),
            Block("Test", emptyArray()),
            Block("Test", emptyArray()),
            Block("Test", emptyArray()),
            Block("Test", emptyArray()),
            Block("Test", emptyArray()),
            Block("Test", emptyArray())
    )

    override fun getNewData() {
        mView?.setupUserRouteAdapter(Routes)
    }

}