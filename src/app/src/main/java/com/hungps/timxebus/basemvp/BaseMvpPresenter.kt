package com.hungps.timxebus.basemvp

/**
 * Created by scit on 11/12/17.
 */

open class BaseMvpPresenter<V : BaseMvpContract.View> : BaseMvpContract.Presenter<V> {
    protected var mView: V? = null

    override fun attachView(view: V) {
        mView = view
    }

    override fun detachView() {
        mView = null
    }

}