package com.daekyung.localweather.network.service

import android.text.TextUtils
import com.daekyung.localweather.util.getServiceEndpoint
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ServiceGenerator {

    private val httpClient = OkHttpClient.Builder()

    private val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .setLenient()
            .create()

    private val interceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)


    private val builder = Retrofit.Builder()

            .baseUrl(getServiceEndpoint())
            .client(OkHttpClient.Builder().addInterceptor(interceptor).build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))

    private var retrofit = builder.build()


    fun <S> create(serviceClass: Class<S>): S {
        return create(serviceClass, null)
    }

    fun <S> create(
            serviceClass: Class<S>, authToken: String?): S {
        if (!TextUtils.isEmpty(authToken)) {
            val interceptor = AuthenticationInterceptor(authToken)

            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor)

                builder.client(httpClient.build())
                retrofit = builder.build()
            }
        }

        return retrofit.create(serviceClass)
    }

}