package com.daekyung.localweather.network.provider

import com.daekyung.localweather.network.httpclient.WeatherHttpClient
import com.daekyung.localweather.network.service.ServiceGenerator
import com.daekyung.localweather.network.service.WeatherService

object WeatherProvider {

    fun provideWeather(): WeatherHttpClient {
        return WeatherHttpClient(ServiceGenerator.create(WeatherService::class.java))
    }
}