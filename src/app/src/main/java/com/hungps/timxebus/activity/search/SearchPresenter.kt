package com.hungps.timxebus.activity.search

import android.content.Intent
import com.hungps.timxebus.activity.searchresult.SearchResultActivity
import com.hungps.timxebus.basemvp.BaseMvpPresenter
import com.hungps.timxebus.model.Route
import com.hungps.timxebus.task.GetRoutesTask
import com.hungps.timxebus.utils.PASSED_ROUTE_DATA

/*
* Author: scit
* Time: 11/12/17
*/

class SearchPresenter : BaseMvpPresenter<SearchContract.View>(),
        SearchContract.Presenter,
        GetRoutesTask.OnFinishedGettingRoutes {


    var mSearchTask: GetRoutesTask? = null



    override fun searchRoutes(from: String, to: String) {
        if (!mView!!.isNetworkConnected()) {
            mView?.showToast("Không có kết nối internet!")
        }

        if (from.isEmpty() || to.isEmpty()) {
            mView?.showToast("Điểm đầu và điểm cuối không được để trống!")
            return
        }

        mView?.showProgressDialog(true)

        mSearchTask = GetRoutesTask(this)
        mSearchTask!!.execute(from, to)
    }



    override fun stopSearchTask() {
        mSearchTask?.cancel(true)
    }



    override fun onFinishedGettingRoutes(routes: MutableList<Route>) {
        if (routes.isNotEmpty()) {
            mView?.showProgressDialog(false)
            val intent = Intent(mView?.getActivity(), SearchResultActivity::class.java)
                    .putParcelableArrayListExtra(PASSED_ROUTE_DATA, ArrayList(routes))

            mView?.openActivity(intent)
            mView?.closeThisActivity()
        } else {
            onErrorGettingRoutes(Exception())
        }
    }



    override fun onErrorGettingRoutes(e: Exception) {
        mView?.showToast("Không tìm thấy lộ trình phù hợp!")
        mView?.showProgressDialog(false)
        mSearchTask?.cancel(true)
    }
}