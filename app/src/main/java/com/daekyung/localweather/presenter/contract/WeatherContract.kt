package com.daekyung.localweather.presenter.contract

import android.content.Context
import co.laxus.coordinates.base.BasePresenter
import co.laxus.coordinates.base.BaseView
import com.daekyung.localweather.adapter.contract.WeatherAdapterContract
import io.reactivex.disposables.CompositeDisposable

interface WeatherContract {

    interface View : BaseView {

        fun doRefresh(isRefresh: Boolean)
        fun finishProgress()
        fun failConnection()
    }

    interface Presenter: BasePresenter<View> {

        var mView: View
        var adapterModel: WeatherAdapterContract.Model
        var adapterView: WeatherAdapterContract.View
        var compositeDisposable: CompositeDisposable

        fun loadItem(context: Context)

    }
}