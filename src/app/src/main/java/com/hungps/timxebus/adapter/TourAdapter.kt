package com.hungps.timxebus.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hungps.timxebus.R
import com.hungps.timxebus.model.UserRoute
import com.hungps.timxebus.utils.inflate
import kotlinx.android.synthetic.main.row_tour_item.view.*

/**
 * Created by scit on 11/12/17.
 */

class TourAdapter(val routes: MutableList<UserRoute>) : RecyclerView.Adapter<TourAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.inflate(R.layout.row_tour_item))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(routes[position])

    override fun getItemCount() = routes.size



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(route: UserRoute) = with(itemView) {
            tenTextView.text = route.name
            diemDiTextView.text = route.goFrom
            diemDenTextView.text = route.goTo
        }
    }
}
