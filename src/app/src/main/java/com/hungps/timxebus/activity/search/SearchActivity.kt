package com.hungps.timxebus.activity.search

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import com.hungps.timxebus.R
import com.hungps.timxebus.basemvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.layout_toolbar.*

/*
* Author: scit
* Time: 11/12/17
*/

class SearchActivity : BaseMvpActivity<SearchContract.View, SearchContract.Presenter>(),
        SearchContract.View, View.OnClickListener {

    override var mPresenter: SearchContract.Presenter = SearchPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

    }

    override fun initViews() {
        super.initViews()

        // Setup Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        // Setup City Spinner
        citySpinner.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listOf("Hà Nội", "Hồ Chí Minh"))
        citySpinner.setSelection(0)

        // Listen to onclick event
        searchButton.setOnClickListener(this)

    }

    override fun onClick(view: View?) = when (view?.id) {

        // On Search Button Clicked
        R.id.searchButton -> mPresenter.searchRoutes(
                fromLocationEditText.text.toString(),
                toLocationEditText.text.toString()
        )

        else -> {}
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }

        return super.onOptionsItemSelected(item)
    }
}