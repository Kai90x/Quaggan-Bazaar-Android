package com.kai.gwtwohot.Fragments

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.kai.gwtwohot.APIServices.QuagganAPI
import com.kai.gwtwohot.Adapters.BaseAdapter
import com.kai.gwtwohot.Adapters.News.NewsAdapter
import com.kai.gwtwohot.Adapters.News.NewsInfo
import com.kai.gwtwohot.Factory.RetrofitFactory
import com.kai.gwtwohot.R
import com.kai.gwtwohot.Serialization.QuagganApi.News.News
import com.kai.gwtwohot.Serialization.QuagganApi.QuagganJson
import com.kai.gwtwohot.Utils.DateUtils
import com.kai.gwtwohot.Utils.NetworkUtils
import com.malinskiy.superrecyclerview.OnMoreListener
import com.malinskiy.superrecyclerview.SuperRecyclerView
import rx.Observable
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Action1
import rx.functions.Func1
import rx.schedulers.Schedulers

/**
 * Created by ikraammoothianpillay1 on 2/20/16.
 */

class NewsFragment : BaseFeedFragment<NewsInfo>() {

    override fun callApi() {
        startProgress()
        val service = RetrofitFactory.createService(QuagganAPI::class.java, QuagganAPI.BaseURL)
        service.news(currentpage,batchSize).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<QuagganJson<News>>() {

                override fun onCompleted() {
                    endProgress()
                }

                override fun onError(e: Throwable) {
                    endProgress()
                    //TODO: Show snackbar to email error
                    e.printStackTrace();
                }

                override fun onNext(newJson: QuagganJson<News>) {
                    totalBatches = newJson.data?.batch?.total!!
                    currentpage = newJson.data?.batch?.current!!

                    var articles = newJson.data?.details?.data
                    if (articles != null)
                        for(article in articles) {
                        val ni = NewsInfo()
                        ni.title = article.title
                        ni.summary = article.description
                        ni.author = if (article.creator == null) " - " else article.creator
                        ni.date = DateUtils.toLocalDate(article.publish_date!!)
                        ni.link = article.link
                        mAdapter?.add(ni)
                    }
                }
        })
    }

    override fun initAdapter(): BaseAdapter<NewsInfo> {
        return NewsAdapter(activity.applicationContext)
    }

    companion object {
        fun newInstance() : NewsFragment {
            val feedFrag = NewsFragment()
            return feedFrag
        }
    }
}
