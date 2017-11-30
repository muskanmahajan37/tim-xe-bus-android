package com.hungps.timxebus.activity.main

import android.app.Activity
import android.app.AlertDialog
import com.hungps.timxebus.basemvp.BaseMvpPresenter
import com.hungps.timxebus.db.DbHelper
import com.hungps.timxebus.model.Route



/*
* Author: scit
* Time: 11/12/17
*/

class MainPresenter() : BaseMvpPresenter<MainContract.View>(), MainContract.Presenter {
    lateinit var mRoutes: MutableList<Route>
    lateinit var mDbHelper: DbHelper


    override fun setupDbHelper(activity: Activity) {
        mDbHelper = DbHelper(activity)
    }



    override fun getFavoriteRoutes(activity: Activity) {
        mRoutes = mDbHelper.favoriteRoutes

        mView?.setupUserRouteAdapter(mRoutes)
    }



    override fun onItemFavoriteClicked(position: Int) {
        // Leave it empty
    }



    override fun onRemoveItem(position: Int) {
        AlertDialog.Builder(mView?.getActivity())
                .setTitle("Xác nhận")
                .setMessage("Bạn có muốn xóa không?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, { dialog, whichButton ->
                    mDbHelper.removeRoute(mRoutes[position])
                    mRoutes.remove(mRoutes[position])

                    mView?.notifyItemRemoved(position)
                    mView?.showToast("Xóa thành công!")
                    mView?.showEmptyText(mRoutes.size == 0)
                })
                .setNegativeButton(android.R.string.no, null)
                .show()
    }

}
