package com.kai.gwtwohot.Fragments

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Spinner
import com.github.clans.fab.FloatingActionButton
import com.github.clans.fab.FloatingActionMenu
import com.kai.gwtwohot.Activities.MainActivity
import com.kai.gwtwohot.Adapters.BaseAdapter
import com.kai.gwtwohot.Extensions.*
import com.kai.gwtwohot.Layouts.WrappableGridLayoutManager
import com.kai.gwtwohot.R
import com.kai.gwtwohot.Utils.NetworkUtils
import com.malinskiy.superrecyclerview.OnMoreListener
import com.malinskiy.superrecyclerview.SuperRecyclerView
import tr.xip.errorview.ErrorView

abstract class BaseFeedFragment<T> : BaseFragment(), SwipeRefreshLayout.OnRefreshListener, OnMoreListener {
    protected var recyclerList: SuperRecyclerView? = null
    protected var fab: FloatingActionMenu? = null
    protected var errorView: ErrorView? = null
    protected var progress: ProgressBar? = null
    protected var batchSize: Int = 25
    protected var currentpage: Int = 1
    protected var totalBatches: Int = 0
    protected var getDataFromAPI: Boolean = true
    protected var mAdapter: BaseAdapter<T>? = null
    protected var mScrollOffset: Int = 4;

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_list, container, false)

        recyclerList = rootView.findViewById(R.id.fragment_list) as SuperRecyclerView
        errorView = rootView.findViewById(R.id.errorView) as ErrorView
        progress = rootView.findViewById(R.id.progressBar) as ProgressBar
        fab = rootView.findViewById(R.id.fab) as FloatingActionMenu

        rootView.circularReveal()
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mAdapter = initAdapter()
        initRecycler()
        kaiActivity?.getSpinner()?.visibility = if (showSpinner()) View.VISIBLE else View.GONE
    }

    private fun initRecycler() {
        if (recyclerList != null) {
            val layout = GridLayoutManager(kaiActivity!!.getContext(),gridSize())
            recyclerList?.setLayoutManager(layout)

            if (shouldLoadMore())
                recyclerList?.setupMoreListener(this, 2)
            if (shouldRefresh()) {
                recyclerList?.setRefreshListener(this)
                recyclerList?.setRefreshingColorResources(android.R.color.holo_orange_light, android.R.color.holo_blue_light, android.R.color.holo_green_light, android.R.color.holo_red_light)
            }

            if (showFloatingButton()) {
                recyclerList?.setOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy);
                        if (Math.abs(dy) > mScrollOffset) {
                            if (dy > 0) {
                                fab?.showMenu(true);
                            } else {
                                fab?.hideMenu(true);
                            }
                        }
                    }
                });
            } else  {
                fab?.hideMenu(true);
            }

            recyclerList?.adapter = mAdapter
            getFeed()
        }
    }

    protected  fun startProgress() {
        if (mAdapter?.isEmpty()!!) {
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

    protected fun getFeed() {
        if (getDataFromAPI) {
            if (NetworkUtils.isNetworkAvailable(kaiActivity!!.getContext())) {
                callApi()
            } else
                showErrorView(R.string.dialog_no_network)
        } else {
            getOfflineData()
        }
    }

    protected open fun showFloatingButton() : Boolean = false
    protected open fun showSpinner() : Boolean = false

    abstract fun callApi()
    abstract fun getOfflineData()
    abstract fun initAdapter() : BaseAdapter<T>
    abstract fun shouldLoadMore() : Boolean
    abstract fun shouldRefresh() : Boolean
    abstract fun gridSize() : Int
}
