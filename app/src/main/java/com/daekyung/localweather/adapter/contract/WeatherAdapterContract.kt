package com.daekyung.localweather.adapter.contract

import com.daekyung.localweather.network.response.ResponseLocationWoeid

interface WeatherAdapterContract: BaseAdapterContract {

    interface View: BaseAdapterContract.View

    interface Model: BaseAdapterContract.Model<ResponseLocationWoeid>
}