package com.kai.gwtwohot.Fragments

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.util.Log
import android.view.View
import android.widget.Toast
import com.kai.gwtwohot.APIServices.QuagganAPI
import com.kai.gwtwohot.Adapters.News.NewsAdapter
import com.kai.gwtwohot.Adapters.News.NewsInfo
import com.kai.gwtwohot.Factory.RetrofitFactory
import com.kai.gwtwohot.R
import com.kai.gwtwohot.Serialization.QuagganApi.News.News
import com.kai.gwtwohot.Serialization.QuagganApi.QuagganJson
import com.kai.gwtwohot.Utils.NetworkUtils
import com.malinskiy.superrecyclerview.OnMoreListener
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by ikraammoothianpillay1 on 2/20/16.
 */

class NewsFragment : BaseFeedFragment(), SwipeRefreshLayout.OnRefreshListener, OnMoreListener {

    private var newsAdapter: NewsAdapter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //setToolBarSubTitle(getString(R.string.news))

        recyclerList?.setupMoreListener(this, 1)
        recyclerList?.setRefreshListener(this)
        recyclerList?.setRefreshingColorResources(android.R.color.holo_orange_light, android.R.color.holo_blue_light, android.R.color.holo_green_light, android.R.color.holo_red_light)

        newsAdapter = NewsAdapter(activity.applicationContext)
        recyclerList?.adapter = newsAdapter
        if (NetworkUtils.isNetworkAvailable(activity)) {
            getNewsFeed()
        } else {
            recyclerList?.visibility = View.GONE
            //showErrorMessage(getString(R.string.dialog_no_network))
        }
    }

    private fun getNewsFeed() {
        if (NetworkUtils.isNetworkAvailable(activity)) {
            val service = RetrofitFactory.createService(QuagganAPI::class.java, QuagganAPI.BaseURL)
            service.news(currentpage,5).subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : Subscriber<QuagganJson<News>>() {

                    override fun onCompleted() {
                        //stopRecyclerViewLoad()
                    }

                    override fun onError(e: Throwable) {
                        stopRecyclerViewLoad()
                        Log.e("Error",e.message)
                    }

                    override fun onNext(newJson: QuagganJson<News>) {
                        totalBatches = newJson.data?.batch?.total!!
                        currentpage = newJson.data?.batch?.current!!

                        var articles = newJson.data?.details?.data as List<News>

                        for(article in articles) {
                            val ni = NewsInfo()
                            ni.title = article.title
                            ni.summary = article.description
                            ni.author = if (article.creator == null) " - " else article.creator
                            //ni.date = if (article.publish_date) " - " else Helper.convertUTCToLocalTime(article.publish_date!!, dtf)
                            ni.link = article.link

                            newsAdapter?.add(ni)
                        }
                    }
            })
        } else
            Toast.makeText(activity, getString(R.string.dialog_no_network), Toast.LENGTH_SHORT).show()

    }

    override fun onMoreAsked(i: Int, i1: Int, i2: Int) {
        if (totalBatches == 0 || currentpage < totalBatches) {
            currentpage++
            getNewsFeed()
        } else
            recyclerList?.hideMoreProgress()
    }

    override fun onRefresh() {
        currentpage = 1
        if (newsAdapter != null) {
            newsAdapter?.clear()
            getNewsFeed()
        }
    }
}
