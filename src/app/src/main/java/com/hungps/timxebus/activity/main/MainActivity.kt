package com.hungps.timxebus.activity.main

import android.os.Bundle
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import com.hungps.timxebus.R
import com.hungps.timxebus.adapter.TourAdapter
import com.hungps.timxebus.basemvp.BaseMvpActivity
import com.hungps.timxebus.model.UserRoute
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_list_tour.*

/**
 * Created by scit on 11/12/17.
 */

class MainActivity : BaseMvpActivity<MainContract.View, MainContract.Presenter>(), MainContract.View {
    lateinit var mDrawerToggle: ActionBarDrawerToggle

    override var mPresenter: MainContract.Presenter = MainPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initToolbar()



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

    override fun initToolbar() {
        mDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close)
        drawerLayout.addDrawerListener(mDrawerToggle)
        mDrawerToggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true
        }

        return super.onOptionsItemSelected(item)

    }
}
