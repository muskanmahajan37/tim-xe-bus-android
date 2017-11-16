package com.hungps.timxebus.adapter

import android.app.Activity
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.hungps.timxebus.R
import com.hungps.timxebus.model.UserRoute
import com.hungps.timxebus.utils.inflate
import com.hungps.timxebus.utils.setVisible
import kotlinx.android.synthetic.main.row_tour_item.view.*

/**
 * Created by scit on 11/12/17.
 */

class RouteAdapter(val activity: Activity, val routes: MutableList<UserRoute>) : RecyclerView.Adapter<RouteAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.inflate(R.layout.row_tour_item))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(routes[position])

    override fun getItemCount() = routes.size



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        fun bind(route: UserRoute) = with(itemView) {
            tenTextView.text = route.name
            diemDiTextView.text = route.goFrom
            diemDenTextView.text = route.goTo

            tourItemLayout.setOnClickListener(this@ViewHolder)
            favoriteButon.setOnClickListener(this@ViewHolder)
        }

        override fun onClick(p0: View?) = with(itemView) {
            when (itemView.id) {

                // On expand an item
                R.id.tourItemLayout -> {
                    val isVisible = expandContentLayout.visibility == View.VISIBLE

                    expandContentLayout.setVisible(!isVisible)
                    divider.setVisible(!isVisible)

                    expandItemButton.setImageDrawable(ContextCompat.getDrawable(
                            activity,
                            if (!isVisible) R.drawable.ic_keyboard_arrow_up else R.drawable.ic_keyboard_arrow_down
                    ))
                }

                // On click favorite an item
                R.id.favoriteButon -> {
                    favoriteButon.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.ic_star))
                }
            }
        }
    }
}
