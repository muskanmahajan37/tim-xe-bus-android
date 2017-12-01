package com.hungps.timxebus.activity.main

import android.app.Activity
import android.app.AlertDialog
import com.hungps.timxebus.basemvp.BaseMvpPresenter
import com.hungps.timxebus.db.DbHelper
import com.hungps.timxebus.model.Route
import android.content.DialogInterface
import android.text.Editable
import android.widget.EditText
import android.widget.RelativeLayout
import com.hungps.timxebus.utils.setMargins
import android.widget.TextView
import android.widget.TableLayout
import android.view.ViewGroup
import android.widget.FrameLayout






/*
* Author: scit
* Time: 11/12/17
*/

class MainPresenter : BaseMvpPresenter<MainContract.View>(), MainContract.Presenter {

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

    override fun onRenameItem(position: Int) {
        val params = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(52,0,52,0)

        val nameEditText = EditText(mView!!.getActivity())
        nameEditText.setText(mRoutes[position].name)
        nameEditText.layoutParams = params

        val container = FrameLayout(mView!!.getActivity())
        container.addView(nameEditText)



        AlertDialog.Builder(mView?.getActivity())
                .setTitle("Đổi tên")
                .setMessage("Nhập tên tuyến:")
                .setView(container)
                .setPositiveButton("Đổi", { _, _ ->
                    var name = nameEditText.text.toString()
                    if (name.isEmpty()) {
                        name = "Tuyến chưa đặt tên"
                    }

                    mRoutes[position].name = name
                    mDbHelper.updateRoute(position, mRoutes[position])
                    mView?.showToast("Đổi tên thành công!")
                    mView?.notifyItemChanged(position)
                })
                .setNegativeButton("Hủy", null)
                .show()
    }


    override fun onRemoveItem(position: Int) {
        AlertDialog.Builder(mView?.getActivity())
                .setTitle("Xác nhận")
                .setMessage("Bạn có muốn xóa không?")
                .setPositiveButton("Có", { _, _ ->
                    mDbHelper.removeRoute(mRoutes[position])
                    mRoutes.remove(mRoutes[position])

                    mView?.notifyItemRemoved(position)
                    mView?.showToast("Xóa thành công!")
                    mView?.showEmptyText(mRoutes.size == 0)
                })
                .setNegativeButton("Không", null)
                .show()
    }

}
