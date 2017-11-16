package com.hungps.timxebus.activity.main

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import com.hungps.timxebus.R
import com.hungps.timxebus.activity.search.SearchActivity
import com.hungps.timxebus.adapter.RouteAdapter
import com.hungps.timxebus.basemvp.BaseMvpActivity
import com.hungps.timxebus.model.UserRoute
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_toolbar.*

/**
* Author: scit
* Time: 11/12/17.
*/

class MainActivity : BaseMvpActivity<MainContract.View, MainContract.Presenter>(),
        MainContract.View, View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    lateinit var mDrawerToggle: ActionBarDrawerToggle

    override var mPresenter: MainContract.Presenter = MainPresenter()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()


        // Fake data
        routeRecyclerView.setHasFixedSize(true)
        routeRecyclerView.layoutManager = LinearLayoutManager(this)
        routeRecyclerView.adapter = RouteAdapter(this, mutableListOf(
                UserRoute("Test", "Test2", "Test3", mutableListOf()),
                UserRoute("Test", "Test2", "Test3", mutableListOf()),
                UserRoute("Test", "Test2", "Test3", mutableListOf()),
                UserRoute("Test", "Test2", "Test3", mutableListOf()),
                UserRoute("Test", "Test2", "Test3", mutableListOf())
        ))
    }



    override fun initViews() {
        // Setup Toolbar
        initToolbar()

        // Listen to navigation drawer item click event
        navigationView.setNavigationItemSelectedListener(this)

        // Listen to button click event
        searchButton.setOnClickListener(this)
    }

    override fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close)
        mDrawerToggle.isDrawerIndicatorEnabled = true
        drawerLayout.addDrawerListener(mDrawerToggle)
        mDrawerToggle.syncState()
    }

    override fun onClick(view: View?) = when (view?.id) {
        R.id.searchButton -> switchActivity(SearchActivity::class.java)

        else -> {}
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.navBookmark -> {  }

            R.id.navSearch -> switchActivity(SearchActivity::class.java)

            R.id.navAppInfo -> {  }

            else -> return false
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

}
