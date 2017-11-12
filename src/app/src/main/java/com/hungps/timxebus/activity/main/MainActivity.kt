package com.hungps.timxebus.activity.main

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.hungps.timxebus.R
import com.hungps.timxebus.adapter.TourAdapter
import com.hungps.timxebus.basemvp.BaseMvpActivity
import com.hungps.timxebus.model.UserRoute
import kotlinx.android.synthetic.main.fragment_list_tour.*

/**
 * Created by scit on 11/12/17.
 */

class MainActivity : BaseMvpActivity<MainContract.View, MainContract.Presenter>(), MainContract.View {

    override var mPresenter: MainContract.Presenter = MainPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tourRecyclerView.setHasFixedSize(true)
        tourRecyclerView.layoutManager = LinearLayoutManager(this)
        tourRecyclerView.adapter = TourAdapter(mutableListOf(
                UserRoute("Test", "Test2", "Test3", mutableListOf()),
                UserRoute("Test", "Test2", "Test3", mutableListOf()),
                UserRoute("Test", "Test2", "Test3", mutableListOf()),
                UserRoute("Test", "Test2", "Test3", mutableListOf()),
                UserRoute("Test", "Test2", "Test3", mutableListOf())
        ))
    }


}