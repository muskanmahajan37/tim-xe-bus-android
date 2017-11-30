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
import com.hungps.timxebus.utils.setVisible
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_list.*
import kotlinx.android.synthetic.main.layout_toolbar.*

/**
* Author: scit
* Time: 11/12/17.
*/

class MainActivity : BaseMvpActivity<MainContract.View, MainContract.Presenter>(),
        MainContract.View,
        View.OnClickListener,
        NavigationView.OnNavigationItemSelectedListener {


    override var mPresenter: MainContract.Presenter = MainPresenter()

    lateinit var mDrawerToggle: ActionBarDrawerToggle
    lateinit var mAdapter: RouteAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mPresenter.setupDbHelper(this)
        mPresenter.getFavoriteRoutes(this)
    }



    override fun onResume() {
        super.onResume()

        mPresenter.getFavoriteRoutes(this)
    }



    override fun initViews() {
        // Setup Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Tuyến đã lưu"


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



    override fun setupUserRouteAdapter(routes: MutableList<Route>) {
        mAdapter = RouteAdapter(this, routes)
        mAdapter.setOnItemClickListener(mPresenter)

        routeRecyclerView.adapter = mAdapter

        showEmptyText(routes.size == 0)
    }



    override fun notifyItemRemoved(position: Int) {
        mAdapter.notifyItemRemoved(position)
    }


    override fun showEmptyText(isShow: Boolean) {
        emptyTextView.setVisible(isShow)
    }



    override fun onClick(view: View?) = when (view?.id) {
        // On Search Button Clicked
        R.id.searchButton -> openActivity(SearchActivity::class.java)

        // Leave empty for default onclick event
        else -> {}
    }



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
