package com.daekyung.localweather.presenter.base

import com.daekyung.localweather.base.BaseView


interface BasePresenter<in VIEW : BaseView> {

    /**
     * View Attach.
     */
    fun attachView(view: VIEW)

    /**
     * View detach
     */
    fun detachView()
}