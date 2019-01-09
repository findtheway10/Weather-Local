package com.daekyung.localweather.viewholder

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.daekyung.localweather.databinding.RecyclerViewItemWeatherBinding
import com.daekyung.localweather.network.response.ResponseLocationWoeid
import com.daekyung.localweather.util.getWeatherIcon
import java.math.RoundingMode
import java.text.DecimalFormat

class WeatherViewHolder(
    private val mContext: Context,
    private val binding: RecyclerViewItemWeatherBinding
) : BaseViewHolder<ResponseLocationWoeid>(binding.root) {

    override fun onBindView(item: ResponseLocationWoeid, position: Int) {

        val df = DecimalFormat("#.#")
        df.roundingMode = RoundingMode.CEILING

        binding.recyclerViewItemTextView.text = item.title

        //Today weather index 0
        binding.recyclerViewItemTextViewWeather1.text = item.consolidated_weather?.get(0)?.weather_state_name ?: ""
        binding.recyclerViewItemTextTemp1.text = df.format(item.consolidated_weather?.get(0)?.the_temp)+"°C"
        binding.recyclerViewItemTextHum1.text = item.consolidated_weather?.get(0)?.humidity.toString() + "%"

        Glide
            .with(mContext)
            .load(getWeatherIcon(item.consolidated_weather?.get(0)?.weather_state_abbr))
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(binding.recyclerViewItemImageView1)

        //Tomorrow weather index 1
        binding.recyclerViewItemTextViewWeather2.text = item.consolidated_weather?.get(1)?.weather_state_name ?: ""
        binding.recyclerViewItemTextTemp2.text = df.format(item.consolidated_weather?.get(1)?.the_temp)+"°C"
        binding.recyclerViewItemTextHum2.text = item.consolidated_weather?.get(1)?.humidity.toString() + "%"

        Glide
            .with(mContext)
            .load(getWeatherIcon(item.consolidated_weather?.get(1)?.weather_state_abbr))
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(binding.recyclerViewItemImageView2)
    }

}