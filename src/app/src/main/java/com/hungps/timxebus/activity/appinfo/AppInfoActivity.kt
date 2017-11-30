package com.hungps.timxebus.activity.appinfo

import android.os.Bundle
import android.view.MenuItem
import com.hungps.timxebus.R
import com.hungps.timxebus.basemvp.BaseMvpActivity
import kotlinx.android.synthetic.main.layout_toolbar.*

/*
* Author: scit
* Time: 11/12/17
*/

class AppInfoActivity : BaseMvpActivity<AppInfoContract.View, AppInfoContract.Presenter>(), AppInfoContract.View {

    override var mPresenter: AppInfoContract.Presenter = AppInfoPresenter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appinfo)

    }



    override fun initViews() {
        super.initViews()

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Thông Tin Ứng Dụng"
    }



    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }

        return super.onOptionsItemSelected(item)
    }
}