package com.hungps.timxebus.activity.splash

import android.os.Bundle
import com.hungps.timxebus.R
import com.hungps.timxebus.basemvp.BaseMvpActivity

/**
 * Created by scit on 11/12/17.
 */

class SplashActivity : BaseMvpActivity<SplashContract.View, SplashContract.Presenter>(), SplashContract.View {

    override var mPresenter: SplashContract.Presenter = SplashPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mPresenter.switchActivityAfter(3)
    }
}