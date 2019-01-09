package com.daekyung.localweather.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class BaseViewHolder<in ITEM>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun onBindView(item: ITEM, position: Int)


}
