package com.hungps.timxebus.activity.search

import com.hungps.timxebus.activity.main.MainActivity
import com.hungps.timxebus.basemvp.BaseMvpPresenter
import com.hungps.timxebus.task.GetRoutesTask

/**
 * Created by scit on 11/12/17.
 */

class SearchPresenter : BaseMvpPresenter<SearchContract.View>(), SearchContract.Presenter {

    override fun searchRoutes(from: String, to: String) {
        if (from.isEmpty() || to.isEmpty()) return

        GetRoutesTask().execute(from, to)
    }

}