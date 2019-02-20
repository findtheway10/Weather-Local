package com.daekyung.localweather.viewholder

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.daekyung.localweather.databinding.RecyclerViewItemWeatherBinding
import com.daekyung.localweather.network.response.ResponseLocationWoeid
import com.daekyung.localweather.util.getWeatherIcon

class WeatherViewHolder(
    private val mContext: Context,
    private val binding: RecyclerViewItemWeatherBinding
) : BaseViewHolder<ResponseLocationWoeid>(binding.root) {

    override fun onBindView(item: ResponseLocationWoeid, position: Int) {

        binding.item = item
        binding.executePendingBindings()

        Glide
            .with(mContext)
            .load(getWeatherIcon(item.consolidated_weather?.get(0)?.weather_state_abbr))
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(binding.recyclerViewItemImageView1)

        Glide
            .with(mContext)
            .load(getWeatherIcon(item.consolidated_weather?.get(1)?.weather_state_abbr))
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(binding.recyclerViewItemImageView2)
    }

}