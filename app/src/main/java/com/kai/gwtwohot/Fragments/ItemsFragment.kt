package com.kai.gwtwohot.Fragments

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.Log
import com.kai.gwtwohot.APIServices.QuagganAPI
import com.kai.gwtwohot.Adapters.BaseAdapter
import com.kai.gwtwohot.Adapters.Items.ItemAdapter
import com.kai.gwtwohot.Adapters.Items.ItemInfo
import com.kai.gwtwohot.Dialogs.ApiHelpDialog
import com.kai.gwtwohot.Dialogs.ItemSearchDialog
import com.kai.gwtwohot.Dialogs.OnDialogResultListener
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

class ItemsFragment : BaseFeedFragment<ItemInfo>(), OnDialogResultListener {
    var name: String? = null
    var type: String? = null
    var subtype: String? = null
    var rarity: String? = null
    var min_level: Int? = null
    var max_level: Int? = null
    var min_buy_price: Int? = null
    var max_buy_price: Int? = null
    var min_sell_price: Int? = null
    var max_sell_price: Int? = null
    var order_by: Int? = null
    var ascOrDesc: Int? = null
    var isLight: Int? = 0

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setToolbarTitle(R.string.items)
        fab?.menuIconView?.setImageDrawable(ContextCompat.getDrawable(activity,R.drawable.gw_find))
        fab?.isIconAnimated = false
        fab?.setOnMenuButtonClickListener {
            var itemSearchDialog = ItemSearchDialog.newInstance(name,
                    type,
                    subtype,
                    min_buy_price?.toString(),
                    max_buy_price?.toString(),
                    min_sell_price?.toString(),
                    max_sell_price?.toString(),
                    rarity,
                    min_level?.toString(),
                    max_level?.toString(),
                    order_by?.toString(),
                    ascOrDesc?.toString())
            itemSearchDialog.show(kaiActivity?.getActivity()?.supportFragmentManager, null)
        }
    }

    override fun callApi() {
        startProgress()
        try {
            val service = RetrofitFactory.createService(QuagganAPI::class.java, QuagganAPI.BaseURL)
            service.items(name,type,subtype,min_buy_price,max_buy_price,min_sell_price,max_sell_price,rarity,min_level,max_level,
                    order_by,ascOrDesc,isLight,1,
                    currentpage,batchSize).subscribeOn(Schedulers.newThread())
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

    override fun onPositiveResult(args: Map<String, String?>) {
        name = args.get(ItemSearchDialog.NAME)
        type = args.get(ItemSearchDialog.TYPE)
        subtype = args.get(ItemSearchDialog.SUBTYPE)
        rarity = args.get(ItemSearchDialog.RARITY)
        min_level = if (args.get(ItemSearchDialog.MIN_LEVEL) != null) args.get(ItemSearchDialog.MIN_LEVEL)?.toInt() else null
        max_level = if (args.get(ItemSearchDialog.MAX_LEVEL) != null) args.get(ItemSearchDialog.MAX_LEVEL)?.toInt() else null
        min_buy_price = if (args.get(ItemSearchDialog.MIN_BUY_PRICE) != null) args.get(ItemSearchDialog.MIN_BUY_PRICE)?.toInt() else null
        max_buy_price = if (args.get(ItemSearchDialog.MAX_BUY_PRICE) != null) args.get(ItemSearchDialog.MAX_BUY_PRICE)?.toInt() else null
        min_sell_price = if (args.get(ItemSearchDialog.MIN_SELL_PRICE) != null) args.get(ItemSearchDialog.MIN_SELL_PRICE)?.toInt() else null
        max_sell_price = if (args.get(ItemSearchDialog.MAX_SELL_PRICE) != null) args.get(ItemSearchDialog.MAX_SELL_PRICE)?.toInt() else null
        if (args.get(ItemSearchDialog.ORDER_BY) != null) {
            order_by = args.get(ItemSearchDialog.ORDER_BY)?.split("-")!![0].toInt()
            ascOrDesc = args.get(ItemSearchDialog.ORDER_BY)?.split("-")!![1].toInt()
        }

        setToolbarSubtitleTitle(if (type.isNullOrEmpty().not()) type!! else "")
        super.onRefresh()
    }

    override fun onNegativeResult() {
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