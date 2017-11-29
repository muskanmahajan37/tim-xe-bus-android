package com.hungps.timxebus.basemvp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.hungps.timxebus.utils.isNetworkConnected

/*
* Author: scit
* Time: 11/12/17
*/

abstract class BaseMvpActivity<in V : BaseMvpContract.View, T : BaseMvpContract.Presenter<V>> : AppCompatActivity(), BaseMvpContract.View  {
    protected abstract var mPresenter: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mPresenter.attachView(this as V)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        initViews()
    }

    override fun initViews() {
        // For override
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun switchActivity(targetClass: Class<*>) {
        startActivity(Intent(this, targetClass))
        finish()
    }

    override fun openActivity(targetClass: Class<*>) {
        startActivity(Intent(this, targetClass))
    }

    override fun openActivity(intent: Intent) {
        startActivity(intent)
    }

    override fun getActivity(): Activity = this

    override fun isNetworkConnected() = isNetworkConnected(this)
}