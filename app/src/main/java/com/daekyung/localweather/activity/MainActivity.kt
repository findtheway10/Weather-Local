package com.daekyung.localweather.activity

import android.content.DialogInterface
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.daekyung.localweather.R
import com.daekyung.localweather.activity.base.BasePresenterActivity
import com.daekyung.localweather.adapter.WeatherAdapter
import com.daekyung.localweather.databinding.ActivityMainBinding
import com.daekyung.localweather.presenter.WeatherPresenter
import com.daekyung.localweather.presenter.contract.WeatherContract
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BasePresenterActivity<WeatherContract.View, WeatherContract.Presenter>(), WeatherContract.View {

    //private lateinit var presenter: WeatherPresenter
    private lateinit var adapter: WeatherAdapter

    private lateinit var binding: ActivityMainBinding

    override fun onCreatePresenter(): WeatherPresenter {
        return WeatherPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        adapter = WeatherAdapter(this@MainActivity)
        recyclerView.adapter = adapter

        val linearLayoutManager = LinearLayoutManager(
            this@MainActivity, LinearLayoutManager.VERTICAL, false)

        recyclerView.layoutManager = linearLayoutManager

        presenter.apply {
            compositeDisposable = CompositeDisposable()
            adapterModel = adapter
            adapterView = adapter
        }

        presenter.loadItem(this@MainActivity)

        binding.swipeLayout.setOnRefreshListener { presenter.loadItem(this@MainActivity) }

    }

    override fun doRefresh(isRefresh: Boolean) {
        binding.swipeLayout.isRefreshing = isRefresh
    }

    override fun finishProgress() {
        binding.progressBar.visibility = View.GONE
    }

    override fun failConnection() {
        makeAlternativeDialog(R.string.dialog_connection_fail_title,
            R.string.dialog_connection_fail_message,
            R.string.dialog_connection_fail_close,
            R.string.dialog_connection_fail_retry,
            DialogInterface.OnClickListener { dialog, which ->
                finish()
            },
            DialogInterface.OnClickListener { dialog, which ->
                presenter.loadItem(this@MainActivity)
            })

    }

    private fun makeAlternativeDialog(titleResId: Int, messageResId: Int, negativeResId: Int, positiveResId: Int,
                                      negativeListener: DialogInterface.OnClickListener,
                                      positiveListener: DialogInterface.OnClickListener) {

        val builder = AlertDialog.Builder(
            this@MainActivity)
        builder.setTitle(titleResId)
        builder.setMessage(messageResId)
        builder.setNegativeButton(negativeResId, negativeListener)
        builder.setPositiveButton(positiveResId, positiveListener)
        builder.show()

    }
}
