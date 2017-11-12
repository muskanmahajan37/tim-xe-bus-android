package com.hungps.timxebus.basemvp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

/**
 * Created by scit on 11/12/17.
 */

abstract class BaseMvpActivity<in V : BaseMvpContract.View, T : BaseMvpContract.Presenter<V>> : AppCompatActivity(), BaseMvpContract.View  {
    protected abstract var mPresenter: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mPresenter.attachView(this as V)
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun switchActivity(targetClass: Class<*>) {
        startActivity(Intent(this, targetClass))
    }
}