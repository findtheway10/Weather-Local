package com.daekyung.localweather.network.response

data class ResponseLocationSearch(
    val latt_long: String,
    val location_type: String,
    val title: String,
    val woeid: Int
)
