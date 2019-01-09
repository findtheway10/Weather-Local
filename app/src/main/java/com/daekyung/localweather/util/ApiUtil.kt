package com.daekyung.localweather.util

const val BASE_URL: String = "https://www.metaweather.com/"

fun getServiceEndpoint() : String {
    return BASE_URL
}

fun getWeatherIcon(keyWord: String?) : String {
    return BASE_URL + "static/img/weather/png/64/$keyWord.png"
}
