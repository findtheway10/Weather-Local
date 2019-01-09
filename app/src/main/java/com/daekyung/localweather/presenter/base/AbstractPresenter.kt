package com.daekyung.localweather.presenter.base

import co.laxus.coordinates.base.BasePresenter
import co.laxus.coordinates.base.BaseView

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