package com.kai.gwtwohot.Fragments

import android.animation.ObjectAnimator
import android.app.Fragment
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.widget.ProgressBar
import android.widget.Toast
import com.kai.gwtwohot.APIServices.QuagganAPI
import com.kai.gwtwohot.Adapters.BaseAdapter
import com.kai.gwtwohot.Adapters.News.NewsInfo
import com.kai.gwtwohot.Factory.RetrofitFactory
import com.kai.gwtwohot.R
import com.kai.gwtwohot.Serialization.QuagganApi.News.News
import com.kai.gwtwohot.Serialization.QuagganApi.QuagganJson
import com.kai.gwtwohot.Utils.NetworkUtils
import com.malinskiy.superrecyclerview.OnMoreListener
import com.malinskiy.superrecyclerview.SuperRecyclerView
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

abstract class BaseFeedFragment<T> : Fragment(), SwipeRefreshLayout.OnRefreshListener, OnMoreListener {
    protected var recyclerList: SuperRecyclerView? = null
    protected var progress: ProgressBar? = null
    protected var batchSize: Int = 25
    protected var currentpage: Int = 1
    protected var totalBatches: Int = 0
    protected var getDataFromAPI: Boolean = true
    protected var mAdapter: BaseAdapter<T>? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_list, container, false)
        recyclerList = rootView.findViewById(R.id.fragment_list) as SuperRecyclerView
        progress = rootView.findViewById(R.id.progressBar) as ProgressBar;
        mAdapter = initAdapter()
        initRecycler()

       if  (android.os.Build.VERSION.SDK_INT >= 21) {
            rootView.addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
                override fun onLayoutChange(v: View, left: Int, top: Int, right: Int, bottom: Int, oldLeft: Int, oldTop: Int,
                                            oldRight: Int, oldBottom: Int) {
                    v.removeOnLayoutChangeListener(this)

                    val cx = (rootView.getLeft() + rootView.getRight()) / 2
                    val cy = (rootView.getTop() + rootView.getBottom()) / 2
                    val finalRadius = Math.max(rootView.getWidth(), rootView.getHeight())

                    val reveal = ViewAnimationUtils.createCircularReveal(v, cx, cy, 0f, finalRadius.toFloat())
                    reveal.interpolator = DecelerateInterpolator(2f)
                    reveal.duration = 1000
                    reveal.start()
                }
            })
        }

        return rootView
    }

    private fun initRecycler() {
        if (recyclerList != null) {
            val layout = LinearLayoutManager(activity)
            recyclerList?.setLayoutManager(layout)

            recyclerList?.setupMoreListener(this, 2)
            recyclerList?.setRefreshListener(this)
            recyclerList?.setRefreshingColorResources(android.R.color.holo_orange_light, android.R.color.holo_blue_light, android.R.color.holo_green_light, android.R.color.holo_red_light)

            recyclerList?.adapter = mAdapter

            getFeed()
        }
    }

    protected  fun startProgress() {
        // see this max value coming back here, we animale towards that value
        if (mAdapter?.itemCount == 0) {
            recyclerList?.hideMoreProgress()
            progress?.visibility = View.VISIBLE
            val animation = ObjectAnimator.ofInt(progress, "progress", 0, 500)
            animation.duration = 1000 //in milliseconds
            animation.repeatCount = Animation.INFINITE
            animation.interpolator = DecelerateInterpolator()
            animation.start ()
        }
    }

    protected fun endProgress() {
        progress?.visibility = View.GONE
        progress?.clearAnimation()
    }

    override fun onMoreAsked(i: Int, i1: Int, i2: Int) {
        if (totalBatches == 0 || currentpage < totalBatches) {
            currentpage++
            getFeed()
        } else
            recyclerList?.hideMoreProgress()
    }

    override fun onRefresh() {
        currentpage = 1
        mAdapter?.clear()
        getFeed()
    }

    private fun getFeed() {
        if (getDataFromAPI) {
            if (NetworkUtils.isNetworkAvailable(activity)) {
                callApi()
            } else
                Toast.makeText(activity, getString(R.string.dialog_no_network), Toast.LENGTH_SHORT).show()
        }
    }

    abstract fun callApi()
    abstract fun initAdapter() : BaseAdapter<T>
}