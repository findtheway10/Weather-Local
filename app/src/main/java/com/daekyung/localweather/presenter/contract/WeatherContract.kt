package com.daekyung.localweather.presenter.contract

import android.content.Context
import com.daekyung.localweather.adapter.contract.WeatherAdapterContract
import com.daekyung.localweather.presenter.base.BasePresenter
import com.daekyung.localweather.presenter.base.BaseView
import io.reactivex.disposables.CompositeDisposable

interface WeatherContract {

    interface View : BaseView {

        fun doRefresh(isRefresh: Boolean)
        fun finishProgress()
        fun failConnection()
    }

    interface Presenter: BasePresenter<View> {

        var adapterModel: WeatherAdapterContract.Model
        var adapterView: WeatherAdapterContract.View
        var compositeDisposable: CompositeDisposable

        fun loadItem(context: Context)

    }
}