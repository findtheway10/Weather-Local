package com.daekyung.localweather.network.response

import com.daekyung.localweather.adapter.WEATHER_VIEW_HOLDER_DEFAULT

data class ResponseLocationWoeid(
    var time: String? = null,
    var sun_rise: String? = null,
    var sun_set: String? = null,
    var timezone_name: String? = null,
    var parent: ParentBean? = null,
    var title: String? = null,
    var location_type: String? = null,
    var woeid: Int = 0,
    var latt_long: String? = null,
    var timezone: String? = null,
    var consolidated_weather: List<ConsolidatedWeatherBean>? = null,
    var sources: List<SourcesBean>? = null,
    var recyclerViewItemType: Int = WEATHER_VIEW_HOLDER_DEFAULT
) {

    class ParentBean {
        var title: String? = null
        var location_type: String? = null
        var woeid: Int = 0
        var latt_long: String? = null
    }

    class ConsolidatedWeatherBean {
        var id: Long = 0
        var weather_state_name: String? = null
        var weather_state_abbr: String? = null
        var wind_direction_compass: String? = null
        var created: String? = null
        var applicable_date: String? = null
        var min_temp: Double = 0.toDouble()
        var max_temp: Double = 0.toDouble()
        var the_temp: Double = 0.toDouble()
        var wind_speed: Double = 0.toDouble()
        var wind_direction: Double = 0.toDouble()
        var air_pressure: Double = 0.toDouble()
        var humidity: Int = 0
        var visibility: Double = 0.toDouble()
        var predictability: Int = 0
    }

    class SourcesBean {
        var title: String? = null
        var slug: String? = null
        var url: String? = null
        var crawl_rate: Int = 0
    }

}