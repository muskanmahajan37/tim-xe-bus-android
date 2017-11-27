package com.hungps.timxebus.adapter

import android.app.Activity
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.hungps.timxebus.R
import com.hungps.timxebus.model.Route
import com.hungps.timxebus.utils.inflate
import com.hungps.timxebus.utils.isVisible
import com.hungps.timxebus.utils.setVisible
import kotlinx.android.synthetic.main.row_route_item.view.*

/*
* Author: scit
* Time: 11/12/17
*/

class RouteAdapter(val activity: Activity, val routes: MutableList<Route>) : RecyclerView.Adapter<RouteAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = ViewHolder(parent.inflate(R.layout.row_route_item))

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
            = holder.bind(routes[position])

    override fun getItemCount() = routes.size



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        fun bind(route: Route) = with(itemView) {
            // Set name
            if (nameTextView.text.isEmpty()) {
                nameTextView.height = 0
            } else {
                nameTextView.text = route.name
            }

            // Set go from/go to
            if (route.blocks.size == 1) {
                goFromTextView.text = route.blocks[0].name
            } else if (route.blocks.size > 2) {
                goFromTextView.text = route.blocks[0].name
                goToTextView.text = route.blocks[1].name
            }

            // Set Detail
            blockRecyclerView.setHasFixedSize(true)
            blockRecyclerView.layoutManager = LinearLayoutManager(activity)
            blockRecyclerView.adapter = BlockAdapter(activity, route.blocks)


            // Listen to click event
            tourItemLayout.setOnClickListener(this@ViewHolder)
            favoriteButon.setOnClickListener(this@ViewHolder)
        }

        override fun onClick(p0: View?) = with(itemView) {
            when (itemView.id) {

                // On expand an item
                R.id.tourItemLayout -> {
                    blockRecyclerView.setVisible(!blockRecyclerView.isVisible)
                    divider.setVisible(!isVisible)

                    expandItemButton.setImageDrawable(ContextCompat.getDrawable(
                            activity,
                            if (!blockRecyclerView.isVisible) R.drawable.ic_keyboard_arrow_up else R.drawable.ic_keyboard_arrow_down
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
