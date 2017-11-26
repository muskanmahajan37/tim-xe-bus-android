package com.hungps.timxebus.activity.searchresult

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import com.hungps.timxebus.R
import com.hungps.timxebus.activity.search.SearchActivity
import com.hungps.timxebus.adapter.RouteAdapter
import com.hungps.timxebus.basemvp.BaseMvpActivity
import com.hungps.timxebus.model.Route
import kotlinx.android.synthetic.main.layout_list.*
import kotlinx.android.synthetic.main.layout_toolbar.*

/**
* Author: scit
* Time: 11/12/17.
*/

class SearchResultActivity : BaseMvpActivity<SearchResultContract.View, SearchResultContract.Presenter>(),
        SearchResultContract.View, View.OnClickListener {

    override var mPresenter: SearchResultContract.Presenter = SearchResultPresenter()

    lateinit var mAdapter: RouteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_searchresult)

        mPresenter.getNewData()
    }



    override fun initViews() {

        // Setup Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        // Setup RecyclerView
        routeRecyclerView.setHasFixedSize(true)
        routeRecyclerView.layoutManager = LinearLayoutManager(this)


        // Listen to events
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
        R.id.searchButton -> switchActivity(SearchActivity::class.java)

        // Leave empty for default onclick event
        else -> {}

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }

        return super.onOptionsItemSelected(item)
    }
}
