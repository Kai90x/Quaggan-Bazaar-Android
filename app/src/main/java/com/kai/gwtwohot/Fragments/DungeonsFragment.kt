package com.kai.gwtwohot.Fragments

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.kai.gwtwohot.APIServices.QuagganAPI
import com.kai.gwtwohot.Adapters.BaseAdapter
import com.kai.gwtwohot.Adapters.Dungeons.DungeonAdapter
import com.kai.gwtwohot.Adapters.Dungeons.DungeonInfo
import com.kai.gwtwohot.Adapters.Events.EventAdapter
import com.kai.gwtwohot.Adapters.Events.EventInfo
import com.kai.gwtwohot.DaoRepositories.DungeonRepository
import com.kai.gwtwohot.DaoRepositories.EventRepository
import com.kai.gwtwohot.Dungeon
import com.kai.gwtwohot.Event
import com.kai.gwtwohot.Extensions.format
import com.kai.gwtwohot.Factory.RetrofitFactory
import com.kai.gwtwohot.R
import com.kai.gwtwohot.Serialization.QuagganApi.Details
import com.kai.gwtwohot.Serialization.QuagganApi.Dungeons.Dungeons
import com.kai.gwtwohot.Serialization.QuagganApi.Events.Events
import com.kai.gwtwohot.Utils.DateUtils
import com.kai.gwtwohot.Utils.ExceptionUtils
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*

/**
 * Created by ikraammoothianpillay1 on 3/12/16.
 */

class DungeonsFragment : BaseFeedFragment<DungeonInfo>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        getDataFromAPI = DungeonRepository.hasAny(activity).not()
        super.onActivityCreated(savedInstanceState)

        setToolbarTitle(R.string.dungeons)
    }

    override fun callApi() {
        startProgress()
        try {
            val dungeonsToday = DungeonRepository.get(activity,Date())
            if (dungeonsToday.any())
                dungeonsToday.forEach { DungeonRepository.delete(activity,it) }

            val service = RetrofitFactory.createService(QuagganAPI::class.java, QuagganAPI.BaseURL)
            service.dungeons().subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : Subscriber<Details<Dungeons>>() {

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

                        override fun onNext(newJson: Details<Dungeons>) {
                            if (newJson.data != null)
                                for (dungeon in newJson.data!!.sortedBy { it.dungeon }) {
                                    val dungeonDao = Dungeon()
                                    dungeonDao.dungeon = dungeon.dungeon
                                    dungeonDao.goldreward = dungeon.goldreward
                                    dungeonDao.isSelected = false
                                    dungeonDao.path = dungeon.path
                                    dungeonDao.tokenreward = dungeon.tokenreward
                                    dungeonDao.dateAdded = Date()

                                    DungeonRepository.insertOrUpdate(activity, dungeonDao)
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

        startProgress()
        var daoDungeons: List<Dungeon> = DungeonRepository.get(activity)

        var prevDungeon = ""
        for (dungeon in daoDungeons) {
            if (prevDungeon.equals(dungeon.dungeon).not()) {
                prevDungeon = dungeon.dungeon
                mAdapter?.add(DungeonInfo(dungeon,true))
            }

            mAdapter?.add(DungeonInfo(dungeon))
        }
        endProgress()
    }

    override fun onRefresh() {
        getDataFromAPI = true
        super.onRefresh()
    }

    override fun initAdapter(): BaseAdapter<DungeonInfo> {
        return DungeonAdapter(kaiActivity!!)
    }

    override fun showFloatingButton() : Boolean = false
    override fun shouldLoadMore(): Boolean = false
    override fun shouldRefresh(): Boolean = true
    override fun showSpinner(): Boolean = false
    override fun gridSize(): Int = 1

    companion object {
        fun newInstance() : DungeonsFragment = DungeonsFragment()
    }
}