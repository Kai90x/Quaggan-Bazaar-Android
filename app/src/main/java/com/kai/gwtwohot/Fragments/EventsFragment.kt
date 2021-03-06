package com.kai.gwtwohot.Fragments

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.kai.gwtwohot.APIServices.QuagganAPI
import com.kai.gwtwohot.Adapters.BaseAdapter
import com.kai.gwtwohot.Adapters.Events.EventAdapter
import com.kai.gwtwohot.Adapters.Events.EventInfo
import com.kai.gwtwohot.DaoRepositories.EventRepository
import com.kai.gwtwohot.Event
import com.kai.gwtwohot.Factory.RetrofitFactory
import com.kai.gwtwohot.R
import com.kai.gwtwohot.Serialization.QuagganApi.Details
import com.kai.gwtwohot.Serialization.QuagganApi.Events.Events
import com.kai.gwtwohot.Utils.DateUtils
import com.kai.gwtwohot.Utils.ExceptionUtils
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*

/**
 * Created by ikraammoothianpillay1 on 3/7/16.
 */
class EventsFragment : BaseFeedFragment<EventInfo>() {

    private var boss: String? = null
    private var sAdapter: ArrayAdapter<String>? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        getDataFromAPI = EventRepository.hasAny(activity).not()
        super.onActivityCreated(savedInstanceState)

        setToolbarTitle(R.string.events)
    }

    override fun callApi() {
        startProgress()
        try {
            val service = RetrofitFactory.createService(QuagganAPI::class.java, QuagganAPI.BaseURL)
            service.events().subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : Subscriber<Details<Events>>() {

                        override fun onCompleted() {
                            endProgress()
                            getDataFromAPI = false
                            getOfflineData()
                        }

                        override fun onError(e: Throwable) {
                            endProgress()
                            showEmailSnackbar(ExceptionUtils.getStackTrace(e))
                            if (mAdapter?.isEmpty()!!) {
                                showErrorView(R.string.dialog_error_occured_2)
                                fab?.hideMenu(true)
                            }
                        }

                        override fun onNext(newJson: Details<Events>) {
                            if (newJson.data != null)
                                for (event in newJson.data!!) {
                                    val eventDao = Event()
                                    eventDao.boss = event.boss
                                    eventDao.zone = event.zone
                                    eventDao.area = event.area

                                    if (event.spawn_time_utc != null) {
                                        var spawn_time = event.spawn_time_utc
                                        val time = spawn_time?.split(":".toRegex())?.dropLastWhile { it.isEmpty() }?.toTypedArray()

                                        val hourToMinutes = Integer.parseInt(time?.get(0)) * 60
                                        val minutes = Integer.parseInt(time?.get(1))

                                        val spawn_time_in_minutes = hourToMinutes + minutes
                                        eventDao.spawntime_utc_minutes = spawn_time_in_minutes
                                    }

                                    EventRepository.insertOrUpdate(activity, eventDao)
                                }
                        }
                    })
            } catch (e: Exception) {
                showErrorView(R.string.dialog_error_occured_2)
                showEmailSnackbar(ExceptionUtils.getStackTrace(e))
            }
    }

    override fun getOfflineData() {
        mAdapter?.clear()

        if (sAdapter == null) {
            var bosses = ArrayList<String>()
            bosses.add(getString(R.string.next_events))
            EventRepository.get(activity).forEach { event -> bosses.add(event.boss) }
            sAdapter = ArrayAdapter<String>(activity, R.layout.text_row, bosses.distinct())
            kaiActivity?.getSpinner()?.adapter = sAdapter
            kaiActivity?.getSpinner()?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(p0: AdapterView<*>?) {

                }

                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    boss = if (kaiActivity?.getSpinner()?.selectedItem.toString() == getString(R.string.next_events)) null else kaiActivity?.getSpinner()?.selectedItem.toString()
                    getOfflineData()
                }
            }
        }

        startProgress()
        var daoEvents: List<Event>? =
                if (boss == null)
                    EventRepository.get(activity,DateUtils.getMinutesOfDay(DateUtils.getUTCDate()!!) - 15)
                else
                    EventRepository.get(activity,boss!!)

        for (event in daoEvents!!) {
            val ei = EventInfo()
            ei.name = event.boss
            ei.location = event.zone
            ei.bossUrl = getString(R.string.url_kai_boss).format(event.boss?.replace(" ", ""))
            ei.time = DateUtils.toLocalDate("%s:%s".format(event.spawntime_utc_minutes / 60, event.spawntime_utc_minutes % 60), "HH:mm", dateExportFormat = "HH:mm")
            val activationMinutes = DateUtils.getMinutesOfDay(DateUtils.getUTCDate()!!) - event.spawntime_utc_minutes
            ei.isActive = (activationMinutes > 0 || activationMinutes == 0)
            mAdapter?.add(ei)
        }
        endProgress()
    }

    override fun onRefresh() {
        getDataFromAPI = true
        EventRepository.clear(activity)
        super.onRefresh()
    }

    override fun initAdapter(): BaseAdapter<EventInfo> {
        return EventAdapter(kaiActivity!!)
    }

    override fun showFloatingButton() : Boolean = false

    override fun shouldLoadMore(): Boolean = false

    override fun shouldRefresh(): Boolean = true
    override fun showSpinner(): Boolean = true
    override fun gridSize(): Int = 1

    companion object {
        fun newInstance() : EventsFragment = EventsFragment()
    }
}