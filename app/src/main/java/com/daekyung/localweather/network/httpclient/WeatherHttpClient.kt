package com.daekyung.localweather.network.httpclient

import com.daekyung.localweather.network.response.ResponseLocationSearch
import com.daekyung.localweather.network.response.ResponseLocationWoeid
import com.daekyung.localweather.network.service.WeatherService

class WeatherHttpClient(private val weatherService: WeatherService) {

    fun getLocationSearch(query: String): io.reactivex.Observable<List<ResponseLocationSearch>> {
        return weatherService.getLocationSearch(query)
    }

    fun getLocationWoeid(woeid: Int): io.reactivex.Observable<ResponseLocationWoeid> {
        return weatherService.getLocationWoeid(woeid)
    }

}