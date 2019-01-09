package com.daekyung.localweather.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.daekyung.localweather.adapter.base.BaseAdapter
import com.daekyung.localweather.adapter.contract.WeatherAdapterContract
import com.daekyung.localweather.databinding.RecyclerViewItemTopBinding
import com.daekyung.localweather.databinding.RecyclerViewItemWeatherBinding
import com.daekyung.localweather.network.response.ResponseLocationWoeid
import com.daekyung.localweather.viewholder.BaseViewHolder
import com.daekyung.localweather.viewholder.WeatherTitleViewHolder
import com.daekyung.localweather.viewholder.WeatherViewHolder

const val WEATHER_VIEW_HOLDER_TITLE = 1
const val WEATHER_VIEW_HOLDER_DEFAULT = 0

class WeatherAdapter(val context: Context) : BaseAdapter<ResponseLocationWoeid>(),
    WeatherAdapterContract.View,
    WeatherAdapterContract.Model {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ResponseLocationWoeid> {
        val layoutInflater = LayoutInflater.from(parent.context)

        return if (viewType == WEATHER_VIEW_HOLDER_TITLE) {
            val itemBinding = RecyclerViewItemTopBinding.inflate(layoutInflater, parent, false)

            WeatherTitleViewHolder(parent.context, itemBinding)
        } else {
            val itemBinding = RecyclerViewItemWeatherBinding.inflate(layoutInflater, parent, false)

            WeatherViewHolder(parent.context, itemBinding)
        }

    }

    override fun getItemViewType(position: Int): Int {

        return if (itemList[position].recyclerViewItemType == WEATHER_VIEW_HOLDER_TITLE) {
            WEATHER_VIEW_HOLDER_TITLE
        } else
            WEATHER_VIEW_HOLDER_DEFAULT
    }
}