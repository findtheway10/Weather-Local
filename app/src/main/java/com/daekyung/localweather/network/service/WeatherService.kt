package com.daekyung.localweather.network.service

import com.daekyung.localweather.network.response.ResponseLocationSearch
import com.daekyung.localweather.network.response.ResponseLocationWoeid
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherService {

    @GET("api/location/search/")
    fun getLocationSearch(@Query("query") query: String): Observable<List<ResponseLocationSearch>>

    @GET("api/location/{woeid}/")
    fun getLocationWoeid(@Path("woeid") woeid: Int): Observable<ResponseLocationWoeid>

}