package com.daekyung.localweather.util

import java.math.RoundingMode
import java.text.DecimalFormat

fun getDecimalFormatTemp(temp: Double): String {

    val df = DecimalFormat("#.#")
    df.roundingMode = RoundingMode.CEILING

    return df.format(temp)

}