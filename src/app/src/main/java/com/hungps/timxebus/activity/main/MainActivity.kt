package com.hungps.timxebus.activity.main

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import com.hungps.timxebus.R
import com.hungps.timxebus.activity.appinfo.AppInfoActivity
import com.hungps.timxebus.activity.search.SearchActivity
import com.hungps.timxebus.adapter.RouteAdapter
import com.hungps.timxebus.basemvp.BaseMvpActivity
import com.hungps.timxebus.model.Block
import com.hungps.timxebus.model.Route
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_list.*
import kotlinx.android.synthetic.main.layout_toolbar.*

/**
* Author: scit
* Time: 11/12/17.
*/

class MainActivity : BaseMvpActivity<MainContract.View, MainContract.Presenter>(),
        MainContract.View, View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    override var mPresenter: MainContract.Presenter = MainPresenter()

    lateinit var mDrawerToggle: ActionBarDrawerToggle
    lateinit var mAdapter: RouteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mPresenter.getFavoriteRoutes(this)
    }



    override fun initViews() {

        // Setup Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        // Setup Drawer
        mDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close)
        mDrawerToggle.isDrawerIndicatorEnabled = true
        mDrawerToggle.syncState()
        drawerLayout.addDrawerListener(mDrawerToggle)


        // Setup RecyclerView
        routeRecyclerView.setHasFixedSize(true)
        routeRecyclerView.layoutManager = LinearLayoutManager(this)


        // Listen to events
        navigationView.setNavigationItemSelectedListener(this)

        searchButton.setOnClickListener(this)

    }



    /**
     * Setup RecyclerView's Adapter
     */
    override fun setupUserRouteAdapter(routes: MutableList<Route>) {
        mAdapter = RouteAdapter(this, routes)

        routeRecyclerView.adapter = mAdapter
    }



    /**
     * On View Clicked Event
     */
    override fun onClick(view: View?) = when (view?.id) {

        // On Search Button Clicked
        R.id.searchButton -> openActivity(SearchActivity::class.java)

        // Leave empty for default onclick event
        else -> {}

    }



    /**
     * On Navigation Item Clicked Event
     */
    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.navBookmark -> { drawerLayout.closeDrawers(); return true }

            R.id.navSearch -> openActivity(SearchActivity::class.java)

            R.id.navAppInfo -> openActivity(AppInfoActivity::class.java)

        }

        return false

    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        // Close Drawer when any drawer item clicked
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true
        }

        return super.onOptionsItemSelected(item)

    }

}
