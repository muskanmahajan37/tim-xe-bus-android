package com.hungps.timxebus.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.TextView
import com.hungps.timxebus.R
import com.hungps.timxebus.utils.inflate

/*
* Author: scit
* Time: 11/28/17
*/

class BlockDetailAdapter(val detail: Array<String>): BaseAdapter() {

    override fun getView(index: Int, view: View?, parent: ViewGroup): View {
        var inflateView = view

        if (inflateView === null) {
            inflateView = parent.inflate(R.layout.row_blockdetail_item)
        }

        inflateView.findViewById<TextView>(android.R.id.text1).setText(detail[index])

        return inflateView
    }

    override fun getItem(index: Int) = detail[index]

    override fun getItemId(index: Int) = index.toLong()

    override fun getCount() = detail.size

}