package com.kai.gwtwohot.Fragments

import android.os.Bundle
import android.util.Log
import com.kai.gwtwohot.APIServices.QuagganAPI
import com.kai.gwtwohot.Adapters.BaseAdapter
import com.kai.gwtwohot.Adapters.Items.ItemAdapter
import com.kai.gwtwohot.Adapters.Items.ItemInfo
import com.kai.gwtwohot.Factory.RetrofitFactory
import com.kai.gwtwohot.R
import com.kai.gwtwohot.Serialization.QuagganApi.Items.Item
import com.kai.gwtwohot.Serialization.QuagganApi.QuagganJson
import com.kai.gwtwohot.Utils.ExceptionUtils
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by ikraammoothianpillay1 on 3/13/16.
 */

class ItemsFragment : BaseFeedFragment<ItemInfo>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setToolbarTitle(R.string.items)
    }

    override fun callApi() {
        startProgress()
        try {
            val service = RetrofitFactory.createService(QuagganAPI::class.java, QuagganAPI.BaseURL)
            service.items(page = currentpage,batch_size = batchSize).subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : Subscriber<QuagganJson<Item>>() {

                        override fun onCompleted() {
                            endProgress()
                        }

                        override fun onError(e: Throwable) {
                            endProgress()
                            Log.e("error",ExceptionUtils.getStackTrace(e))
                            if (mAdapter?.isEmpty()!!) {
                                showEmailSnackbar(ExceptionUtils.getStackTrace(e))
                                showErrorView(R.string.dialog_error_occured_2)
                            }
                        }

                        override fun onNext(newJson: QuagganJson<Item>) {
                            totalBatches = newJson.data?.batch?.total!!
                            currentpage = newJson.data?.batch?.current!!

                            newJson.data?.details?.data?.forEach {
                                var ii = ItemInfo()

                                ii.name = it.name
                                ii.iconUrl = it.icon
                                ii.buyprice = it.price?.buy?.price
                                ii.sellprice = it.price?.sell?.price
                                ii.rarity = it.rarity

                                mAdapter?.add(ii)
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

    override fun initAdapter(): BaseAdapter<ItemInfo> {
        return ItemAdapter(kaiActivity!!)
    }

    override fun showFloatingButton() : Boolean = true
    override fun shouldLoadMore(): Boolean = true
    override fun shouldRefresh(): Boolean = true
    override fun showSpinner(): Boolean = false
    override fun gridSize(): Int = 1

    companion object {
        fun newInstance() : ItemsFragment = ItemsFragment()
    }
}