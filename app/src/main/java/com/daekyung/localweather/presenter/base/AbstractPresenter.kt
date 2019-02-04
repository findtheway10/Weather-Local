package com.daekyung.localweather.presenter.base

import com.daekyung.localweather.base.BaseView

abstract class AbstractPresenter<VIEW : BaseView> : BasePresenter<VIEW> {

    protected var view: VIEW? = null
        private set

    override fun attachView(view: VIEW) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

}