package com.hungps.timxebus.adapter

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ListAdapter
import com.hungps.timxebus.R
import com.hungps.timxebus.model.Block
import com.hungps.timxebus.model.BusBlock
import com.hungps.timxebus.utils.inflate
import com.hungps.timxebus.utils.isVisible
import com.hungps.timxebus.utils.setListViewHeightBasedOnItems
import com.hungps.timxebus.utils.setVisible
import kotlinx.android.synthetic.main.row_block_item.view.*

/*
* Author: scit
* Time: 11/27/17
*/

class BlockAdapter(val activity: Activity, val blocks: MutableList<Block>): RecyclerView.Adapter<BlockAdapter.ViewHolder>() {

    override fun getItemCount() = blocks.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
            = holder.bind(blocks[position])

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = ViewHolder(parent.inflate(R.layout.row_block_item))



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        fun bind(block: Block) = with(itemView) {
            blockNameTextView.text = block.name

            blockDetailListView.adapter = ArrayAdapter<String>(activity, R.layout.row_blockdetail_item, block.detail)
            blockDetailListView.setListViewHeightBasedOnItems()

            if (block is BusBlock) {
                val bus = block.bus
                busNameTextView.setText("Tuyến ${bus.id} - ${bus.goFrom} đi ${bus.goTo}")
            } else {
                busNameTextView.setVisible(false)
            }

            blockNameTextView.setOnClickListener(this@ViewHolder)
        }

        override fun onClick(view: View?) = with(itemView) {
            blockDetailListView.setVisible(!blockDetailListView.isVisible)
        }

    }
}