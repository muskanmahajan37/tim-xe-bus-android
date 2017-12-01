package com.hungps.timxebus.adapter

import android.app.Activity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.hungps.timxebus.R
import com.hungps.timxebus.activity.main.MainActivity
import com.hungps.timxebus.db.DbHelper
import com.hungps.timxebus.model.BusBlock
import com.hungps.timxebus.model.Route
import com.hungps.timxebus.utils.*
import kotlinx.android.synthetic.main.row_route_item.view.*

/*
* Author: scit
* Time: 11/12/17
*/

class RouteAdapter(val mActivity: Activity, val mRoutes: MutableList<Route>, val mDbHelper: DbHelper? = null) : RecyclerView.Adapter<RouteAdapter.ViewHolder>() {

    var mListener: OnRouteListChange? = null



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.inflate(R.layout.row_route_item))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(mRoutes[position])

    override fun getItemCount() = mRoutes.size




    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
            View.OnClickListener {


        fun bind(route: Route) = with(itemView) {
            // Set name
            if (route.name.isEmpty()) {
                nameTextView.height = 0
            } else {
                nameTextView.text = route.name
            }

            // Set go from/go to
            var allBusBlocks = route.blocks.filter { block -> block.type.equals("bus") }
            if (allBusBlocks.size >= 2) {
                goFromTextView.text = (allBusBlocks[0] as BusBlock).bus.toString()
                goToTextView.text = (allBusBlocks[allBusBlocks.size - 1] as BusBlock).bus.toString()
            } else {
                goFromTextView.text = route.blocks[0].name
                goToTextView.text = route.blocks[route.blocks.size - 1].name
            }

            // Set Detail
            blockRecyclerView.setHasFixedSize(true)
            blockRecyclerView.layoutManager = LinearLayoutManager(mActivity)
            blockRecyclerView.adapter = BlockAdapter(mActivity, route.blocks)

            // Set Favorite star's status
            if (mDbHelper === null) {
                favoriteButon.setVisible(false)
                buttonWrapper.setVisible(true) //And show the button wrapper, too
            } else if (mDbHelper.favoriteRoutes.containsRoute(route)) {
                favoriteButon.setIconDrawable(mActivity, R.drawable.ic_star)
                favoriteButon.tag = R.drawable.ic_star
            } else {
                favoriteButon.tag = R.drawable.ic_star_border
            }

            // Listen to click event
            tourItemLayout.setOnClickListener(this@ViewHolder)
            favoriteButon.setOnClickListener(this@ViewHolder)

            if (mActivity is MainActivity) {
                renameTextView.setOnClickListener(this@ViewHolder)
                removeTextView.setOnClickListener(this@ViewHolder)
            }
        }



        override fun onClick(view: View?): Unit = with(itemView) {
            when (view?.id) {
                // On click favorite an item
                R.id.favoriteButon -> {
                    val newStarDrawable =
                            if (favoriteButon.tag == R.drawable.ic_star_border)
                                R.drawable.ic_star
                            else
                                R.drawable.ic_star_border

                    favoriteButon.setIconDrawable(mActivity, newStarDrawable)
                    favoriteButon.tag = newStarDrawable

                    mListener?.onItemFavoriteClicked(adapterPosition)
                }

                // On expand an item
                R.id.tourItemLayout -> {
                    val isShowDetail = !hiddenView.isVisible

                    //Show (or hide) block detail
                    hiddenView.setVisibleWithAnimation(isShowDetail)

                    expandItemButton.setIconDrawable(
                            mActivity,
                            if (isShowDetail)
                                R.drawable.ic_keyboard_arrow_up
                            else
                                R.drawable.ic_keyboard_arrow_down
                    )
                }


                // On rename an item
                R.id.renameTextView -> {
                    mListener?.onRenameItem(adapterPosition)
                }

                // On remove an Item
                R.id.removeTextView -> {
                    mListener?.onRemoveItem(adapterPosition)
                }
            }
        }
    }



    fun setOnItemClickListener(listener: OnRouteListChange) {
        mListener = listener
    }



    interface OnRouteListChange {
        fun onItemFavoriteClicked(position: Int)

        fun onRemoveItem(position: Int)

        fun onRenameItem(position: Int)
    }
}
