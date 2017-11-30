package com.hungps.timxebus.activity.searchresult

import android.content.Context
import android.util.Log
import com.hungps.timxebus.adapter.RouteAdapter
import com.hungps.timxebus.basemvp.BaseMvpPresenter
import com.hungps.timxebus.db.DbHelper
import com.hungps.timxebus.model.Route

/*
* Author: scit
* Time: 11/12/17
*/

class SearchResultPresenter() : BaseMvpPresenter<SearchResultContract.View>(),
        SearchResultContract.Presenter {

    lateinit var mRoutes: MutableList<Route>
    lateinit var mDbHelper: DbHelper

    override fun setupRecyclerView(context: Context, routes: MutableList<Route>) {
        mRoutes = routes
        mDbHelper = DbHelper(context)
        mView?.setupUserRouteAdapter(routes, mDbHelper)
    }

    override fun onItemFavoriteClicked(position: Int) {
        if (mDbHelper.favoriteRoutes.contains(mRoutes[position])) {
            mDbHelper.removeRoute(mRoutes[position])
            mView?.showToast("Đã xóa khỏi danh sách yêu thích")
        } else {
            mDbHelper.addRoute(mRoutes[position])
            mView?.showToast("Đã thêm vào danh sách yêu thích")
        }
    }

    override fun onRemoveItem(position: Int) {
        // For MainActivity only
    }
}
