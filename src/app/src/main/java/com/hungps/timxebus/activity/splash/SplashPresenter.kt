package com.hungps.timxebus.activity.splash

import android.os.Handler
import com.hungps.timxebus.activity.main.MainActivity
import com.hungps.timxebus.basemvp.BaseMvpPresenter

/**
 * Created by scit on 11/12/17.
 */

class SplashPresenter() : BaseMvpPresenter<SplashContract.View>(), SplashContract.Presenter {

    override fun switchActivityAfter(second: Int) {
        val delayMillisecs: Long = second * 1000L

        Handler().postDelayed({
            mView?.switchActivity(MainActivity::class.java)
        }, delayMillisecs)
    }

}