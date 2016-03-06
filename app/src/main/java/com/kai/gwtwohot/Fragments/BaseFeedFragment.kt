package com.kai.gwtwohot.Fragments

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.kai.gwtwohot.Adapters.BaseAdapter
import com.kai.gwtwohot.R
import com.kai.gwtwohot.Utils.NetworkUtils
import com.malinskiy.superrecyclerview.OnMoreListener
import com.malinskiy.superrecyclerview.SuperRecyclerView
import tr.xip.errorview.ErrorView

abstract class BaseFeedFragment<T> : BaseFragment(), SwipeRefreshLayout.OnRefreshListener, OnMoreListener {
    protected var recyclerList: SuperRecyclerView? = null
    protected var errorView: ErrorView? = null
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
        errorView = rootView.findViewById(R.id.errorView) as ErrorView
        progress = rootView.findViewById(R.id.progressBar) as ProgressBar
        mAdapter = initAdapter()
        initRecycler()

        rootView.circularReveal()

        return rootView
    }

    private fun initRecycler() {
        if (recyclerList != null) {
            val layout = LinearLayoutManager(activity!!.getContext())
            recyclerList?.setLayoutManager(layout)

            recyclerList?.setupMoreListener(this, 2)
            recyclerList?.setRefreshListener(this)
            recyclerList?.setRefreshingColorResources(android.R.color.holo_orange_light, android.R.color.holo_blue_light, android.R.color.holo_green_light, android.R.color.holo_red_light)

            recyclerList?.adapter = mAdapter

            getFeed()
        }
    }

    protected  fun startProgress() {
        if (mAdapter?.itemCount == 0) {
            recyclerList?.hideMoreProgress()
            progress?.start()
        }
    }

    protected fun endProgress() {
        progress?.stop()
        recyclerList?.hideMoreProgress()
    }

    protected fun showErrorView(message: Int,title: Int = R.string.dialog_error,showRetry: Boolean = true) {
        errorView?.show(message, {getFeed()},title,showRetry)
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
            if (NetworkUtils.isNetworkAvailable(activity!!.getContext())) {
                callApi()
            } else
                showErrorView(R.string.dialog_no_network)
        }
    }

    abstract fun callApi()
    abstract fun initAdapter() : BaseAdapter<T>
}