package com.daekyung.localweather.base

import android.app.ProgressDialog
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.daekyung.localweather.R
import com.daekyung.localweather.util.BaseActivityProgressDialog
import io.reactivex.disposables.CompositeDisposable

abstract class BaseActivity: AppCompatActivity() {

    private var progressDialog: ProgressDialog? = null

    fun makeToast(text: String) {
        Toast.makeText(this, text,  Toast.LENGTH_SHORT).show()
    }

    fun makeToast(res: Int) {
        Toast.makeText(this, getString(res),  Toast.LENGTH_SHORT).show()
    }

    fun makeNetworkErrorDialog(listener: DialogInterface.OnClickListener){
        val builder = AlertDialog.Builder(
            this@BaseActivity)

        builder.setTitle(R.string.dialog_title_network_error)
        builder.setMessage(R.string.dialog_message_network_error)
        builder.setPositiveButton(R.string.dialog_positive_network_error, listener)
        builder.show()
    }

    fun makeAlternativeDialog(titleResId: Int, messageResId: Int, negativeResId: Int, positiveResId: Int,
                              negativeListener: DialogInterface.OnClickListener,
                              positiveListener: DialogInterface.OnClickListener) {

        val builder = AlertDialog.Builder(
            this@BaseActivity)
        builder.setTitle(titleResId)
        builder.setMessage(messageResId)
        builder.setNegativeButton(negativeResId, negativeListener)
        builder.setPositiveButton(positiveResId, positiveListener)
        builder.show()

    }

    fun makeDialog(titleResId: Int, messageResId: Int, positiveResId: Int,
                   positiveListener: DialogInterface.OnClickListener) {

        val builder = AlertDialog.Builder(
            this@BaseActivity)
        builder.setTitle(titleResId)
        builder.setMessage(messageResId)
        builder.setPositiveButton(positiveResId, positiveListener)
        builder.show()

    }

    fun detachComposite() {
        CompositeDisposable().clear()
    }

    override fun onDestroy() {
        super.onDestroy()
        detachComposite()
    }

    protected fun showProgressDialog() {
        hideProgressDialog()
        progressDialog = BaseActivityProgressDialog(this)
    }

    protected fun hideProgressDialog() {
        if (progressDialog != null) {
            if (progressDialog!!.isShowing) {
                progressDialog!!.cancel()
            }

        }
    }
}