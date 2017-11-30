package com.hungps.timxebus.adapter

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.hungps.timxebus.R
import com.hungps.timxebus.model.Block
import com.hungps.timxebus.model.BusBlock
import com.hungps.timxebus.utils.inflate
import com.hungps.timxebus.utils.isVisible
import com.hungps.timxebus.utils.setIconDrawable
import com.hungps.timxebus.utils.setVisible
import kotlinx.android.synthetic.main.row_block_item.view.*

/*
* Author: scit
* Time: 11/27/17
*/

class BlockAdapter(val activity: Activity, val blocks: MutableList<Block>): RecyclerView.Adapter<BlockAdapter.ViewHolder>() {


    override fun getItemCount() = blocks.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(blocks[position])

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = ViewHolder(parent.inflate(R.layout.row_block_item))




    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
            View.OnClickListener {


        fun bind(block: Block) = with(itemView) {
            blockNameTextView.text = block.name
            blockDetailListView.adapter = BlockDetailAdapter(block.detail)

            if (block is BusBlock) {
                busNameTextView.text = block.bus.toString()
            } else {
                busNameTextView.setVisible(false)
            }

            // Listen to onclick event
            titleWrapper.setOnClickListener(this@ViewHolder)
            collapeImageView.setOnClickListener(this@ViewHolder)
        }



        override fun onClick(view: View?) = with(itemView) {
            val isShowDetail = !blockDetailListView.isVisible

            blockDetailListView.setVisible(isShowDetail)
            collapeImageView.setIconDrawable(
                    activity,
                    if (isShowDetail)
                        R.drawable.ic_keyboard_arrow_up
                    else
                        R.drawable.ic_keyboard_arrow_down
            )
        }

    }
}