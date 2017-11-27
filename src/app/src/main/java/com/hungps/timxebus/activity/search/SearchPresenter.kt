package com.hungps.timxebus.activity.search

import android.content.Intent
import com.hungps.timxebus.activity.searchresult.SearchResultActivity
import com.hungps.timxebus.basemvp.BaseMvpPresenter
import com.hungps.timxebus.model.Block
import com.hungps.timxebus.model.Route
import com.hungps.timxebus.task.GetRoutesTask
import com.hungps.timxebus.utils.PASSED_ROUTE_DATA

/**
 * Created by scit on 11/12/17.
 */

class SearchPresenter : BaseMvpPresenter<SearchContract.View>(), SearchContract.Presenter, GetRoutesTask.OnFinishedGettingRoutes {

    override fun searchRoutes(from: String, to: String) {
        if (from.isEmpty() || to.isEmpty()) {
            mView?.showToast("Điểm đầu và điểm cuối không được để trống!")
            return
        }

        GetRoutesTask(this).execute(from, to)

    }

    override fun onFinishedGettingRoutes(routes: MutableList<Route>) {
        val intent = Intent(mView?.getActivity(), SearchResultActivity::class.java)
                .putParcelableArrayListExtra(PASSED_ROUTE_DATA, ArrayList(routes))

        mView?.openActivity(intent)
    }
}