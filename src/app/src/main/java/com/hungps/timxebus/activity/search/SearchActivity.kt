package com.hungps.timxebus.activity.search

import android.os.Bundle
import android.view.MenuItem
import com.hungps.timxebus.R
import com.hungps.timxebus.activity.searchresult.SearchResultActivity
import com.hungps.timxebus.basemvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.layout_toolbar.*

/**
 * Created by scit on 11/12/17.
 */

class SearchActivity : BaseMvpActivity<SearchContract.View, SearchContract.Presenter>(), SearchContract.View {

    override var mPresenter: SearchContract.Presenter = SearchPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun initViews() {
        super.initViews()

        searchButton.setOnClickListener {
            // TODO: Validate search form
            switchActivity(SearchResultActivity::class.java)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }

        return super.onOptionsItemSelected(item)
    }
}