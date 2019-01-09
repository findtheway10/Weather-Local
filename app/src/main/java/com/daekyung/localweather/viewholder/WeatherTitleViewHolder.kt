package com.daekyung.localweather.viewholder

import android.content.Context
import com.daekyung.localweather.databinding.RecyclerViewItemTopBinding
import com.daekyung.localweather.network.response.ResponseLocationWoeid

class WeatherTitleViewHolder(
    private val mContext: Context,
    private val binding: RecyclerViewItemTopBinding
) : BaseViewHolder<ResponseLocationWoeid>(binding.root) {

    override fun onBindView(item: ResponseLocationWoeid, position: Int) {

    }

}