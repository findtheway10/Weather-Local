package com.daekyung.localweather.adapter.contract

interface BaseAdapterContract {

    interface View {

        fun notifyAdapter()
    }

    interface Model<T> {

        fun addItems(itemList: ArrayList<T>)

        fun addItem(item: T)

        fun addItem(index:Int, item: T)

        fun removeItem(position: Int)

        fun clearItem()

        fun getItem(position: Int): T

        fun setItem(position: Int, item: T)

        fun getItemCount(): Int

    }

}