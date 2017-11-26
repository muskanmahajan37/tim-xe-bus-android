package com.hungps.timxebus.activity.appinfo

import android.os.Bundle
import com.hungps.timxebus.R
import com.hungps.timxebus.basemvp.BaseMvpActivity
import kotlinx.android.synthetic.main.layout_toolbar.*

/**
 * Created by scit on 11/12/17.
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
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }
}