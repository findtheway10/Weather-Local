package com.daekyung.localweather.util

import android.app.ProgressDialog
import android.content.Context

fun BaseActivityProgressDialog(context: Context): ProgressDialog {
    val progressDialog = ProgressDialog(context)
    progressDialog.show()

    progressDialog.isIndeterminate = true
    progressDialog.setCancelable(false)
    progressDialog.setCanceledOnTouchOutside(false)
    return progressDialog
}