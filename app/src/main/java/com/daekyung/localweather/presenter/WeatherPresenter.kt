package com.daekyung.localweather.presenter

import android.content.Context
import com.daekyung.localweather.adapter.WEATHER_VIEW_HOLDER_TITLE
import com.daekyung.localweather.adapter.contract.WeatherAdapterContract
import com.daekyung.localweather.network.provider.WeatherProvider
import com.daekyung.localweather.network.response.ResponseLocationSearch
import com.daekyung.localweather.network.response.ResponseLocationWoeid
import com.daekyung.localweather.presenter.base.AbstractPresenter
import com.daekyung.localweather.presenter.contract.WeatherContract
import com.daekyung.localweather.util.WEATHER_KEY_WORD
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class WeatherPresenter : AbstractPresenter<WeatherContract.View>(), WeatherContract.Presenter {

    override lateinit var adapterModel: WeatherAdapterContract.Model
    override lateinit var adapterView: WeatherAdapterContract.View
    override lateinit var compositeDisposable: CompositeDisposable

    private var isWeatherLoading: Boolean = false
    private val weatherList: ArrayList<ResponseLocationWoeid> = ArrayList()


    override fun detachView() {
        compositeDisposable.clear()
        super.detachView()
    }

    override fun loadItem(context: Context) {

        if (!isWeatherLoading) {

            isWeatherLoading = true

            weatherList.clear()
            adapterModel.clearItem()
            adapterView.notifyAdapter()

            val weather = WeatherProvider.provideWeather()

            compositeDisposable.add(
                weather.getLocationSearch(WEATHER_KEY_WORD)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ result ->
                        loadWeatherByWoeid(result)
                    }, { error ->
                        error.printStackTrace()
                        isWeatherLoading = false
                        view?.failConnection()
                        view?.finishProgress()
                        view?.doRefresh(false)
                    })
            )
        }
    }

    private fun loadWeatherByWoeid(list: List<ResponseLocationSearch>) {

        val mergeList: ArrayList<Observable<ResponseLocationWoeid>> = ArrayList()
        val weather = WeatherProvider.provideWeather()
        (0..(list.size - 1))
            .forEach {
                mergeList.add(weather.getLocationWoeid(list[it].woeid))
            }

        val mergeObservable: Observable<ResponseLocationWoeid> = Observable.merge(mergeList)

        compositeDisposable.add(
            mergeObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    weatherList.add(result)
                }, { error ->
                    error.printStackTrace()

                    isWeatherLoading = false
                    view?.failConnection()
                    view?.finishProgress()
                    view?.doRefresh(false)
                }, {
                    weatherList.let {
                        adapterModel.addItems(it)

                        val titleItem = ResponseLocationWoeid()
                        titleItem.recyclerViewItemType = WEATHER_VIEW_HOLDER_TITLE
                        adapterModel.addItem(0, titleItem)

                        adapterView.notifyAdapter()
                        isWeatherLoading = false

                        view?.doRefresh(false)
                        view?.finishProgress()
                    }
                })
        )
    }

}