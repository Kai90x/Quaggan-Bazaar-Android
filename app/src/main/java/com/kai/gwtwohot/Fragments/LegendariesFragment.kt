package com.kai.gwtwohot.Fragments

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.kai.gwtwohot.APIServices.QuagganAPI
import com.kai.gwtwohot.Adapters.BaseAdapter
import com.kai.gwtwohot.Adapters.Events.EventAdapter
import com.kai.gwtwohot.Adapters.Events.EventInfo
import com.kai.gwtwohot.Adapters.Legendaries.LegendariesAdapter
import com.kai.gwtwohot.Adapters.Legendaries.LegendariesInfo
import com.kai.gwtwohot.DaoRepositories.EventRepository
import com.kai.gwtwohot.Event
import com.kai.gwtwohot.Factory.RetrofitFactory
import com.kai.gwtwohot.R
import com.kai.gwtwohot.Serialization.QuagganApi.Details
import com.kai.gwtwohot.Serialization.QuagganApi.Events.Events
import com.kai.gwtwohot.Serialization.QuagganApi.Legendaries.Legendaries
import com.kai.gwtwohot.Utils.DateUtils
import com.kai.gwtwohot.Utils.ExceptionUtils
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*

/**
 * Created by ikraammoothianpillay1 on 3/12/16.
 */
class LegendariesFragment : BaseFeedFragment<LegendariesInfo>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setToolbarTitle(R.string.legendarycraft)
    }

    override fun callApi() {
        startProgress()
        try {
            val service = RetrofitFactory.createService(QuagganAPI::class.java, QuagganAPI.BaseURL)
            service.legendaries().subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : Subscriber<Details<Legendaries>>() {

                        override fun onCompleted() {
                            endProgress()
                        }

                        override fun onError(e: Throwable) {
                            endProgress()
                            showEmailSnackbar(ExceptionUtils.getStackTrace(e))
                            showErrorView(R.string.dialog_error_occured_2)
                        }

                        override fun onNext(newJson: Details<Legendaries>) {
                            if (newJson.data != null)
                                for (legendary in newJson.data!!) {
                                    var li = LegendariesInfo()
                                    li.iconUrl = legendary.icon
                                    li.id = legendary.id
                                    li.name = legendary.name
                                    li.rarity = legendary.rarity

                                    mAdapter?.add(li)
                                }
                        }
                    })
        } catch (e: Exception) {
            showErrorView(R.string.dialog_error_occured_2)
            showEmailSnackbar(ExceptionUtils.getStackTrace(e))
        }
    }

    override fun getOfflineData() {
    }

    override fun initAdapter(): BaseAdapter<LegendariesInfo> {
        return LegendariesAdapter(kaiActivity!!)
    }

    override fun showFloatingButton() : Boolean = false
    override fun shouldLoadMore(): Boolean = false
    override fun shouldRefresh(): Boolean = true
    override fun showSpinner(): Boolean = false
    override fun gridSize(): Int = 4

    companion object {
        fun newInstance() : LegendariesFragment = LegendariesFragment()
    }
}