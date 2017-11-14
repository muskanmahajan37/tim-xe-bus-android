package com.hungps.timxebus.activity.main

import com.hungps.timxebus.basemvp.BaseMvpContract

/**
 * Created by scit on 11/12/17.
 */

interface MainContract {

    interface View : BaseMvpContract.View {

        fun initToolbar()

    }

    interface Presenter : BaseMvpContract.Presenter<View> {

    }

}