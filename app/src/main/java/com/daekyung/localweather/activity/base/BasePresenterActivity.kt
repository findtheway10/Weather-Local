package com.daekyung.localweather.activity.base

import android.os.Bundle
import com.daekyung.localweather.presenter.base.BasePresenter
import com.daekyung.localweather.presenter.base.BaseView

/**
 * base activity for use presenter
 */
abstract class BasePresenterActivity<in VIEW: BaseView, PRESENTER : BasePresenter<VIEW>> : BaseActivity(), BaseView {

    protected lateinit var presenter: PRESENTER
        private set

    abstract fun onCreatePresenter(): PRESENTER

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = onCreatePresenter()
        presenter.attachView(this as VIEW)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}