package com.hungps.timxebus.basemvp

import android.app.Activity

/**
 * Created by scit on 11/12/17.
 */

interface BaseMvpContract {

    interface View {

        fun initViews()

        fun showToast(message: String)

        fun switchActivity(targetClass: Class<*>)

        fun openActivity(targetClass: Class<*>)

    }

    interface Presenter<in V : View> {

        fun attachView(view: V)

        fun detachView()

    }

}