package com.kai.gwtwohot.Fragments

import android.app.Fragment
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.kai.gwtwohot.Adapters.BaseAdapter
import com.kai.gwtwohot.R
import com.kai.gwtwohot.Utils.NetworkUtils
import com.malinskiy.superrecyclerview.OnMoreListener
import com.malinskiy.superrecyclerview.SuperRecyclerView

abstract class BaseFeedFragment<T> : Fragment(), SwipeRefreshLayout.OnRefreshListener, OnMoreListener {
    protected var recyclerList: SuperRecyclerView? = null
    protected var batchSize: Int = 25
    protected var currentpage: Int = 1
    protected var totalBatches: Int = 0
    protected var mAdapter: BaseAdapter<T>? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_list, container, false)
        recyclerList = rootView.findViewById(R.id.fragment_list) as SuperRecyclerView
        mAdapter = initAdapter()
        initRecycler()
        return rootView
    }

    private fun initRecycler() {
        if (recyclerList != null) {
            val layout = GridLayoutManager(activity,1)
            recyclerList?.setLayoutManager(layout)

            recyclerList?.setupMoreListener(this, 1)
            recyclerList?.setRefreshListener(this)
            recyclerList?.setRefreshingColorResources(android.R.color.holo_orange_light, android.R.color.holo_blue_light, android.R.color.holo_green_light, android.R.color.holo_red_light)

            recyclerList?.adapter = mAdapter
            callApi()
        }
    }

    protected fun stopRecyclerViewLoad() {
        if (recyclerList!!.isLoadingMore)
            recyclerList?.hideMoreProgress()
    }

    override fun onMoreAsked(i: Int, i1: Int, i2: Int) {
        Log.e("","fuigikgvig")
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
        if (NetworkUtils.isNetworkAvailable(activity))
            callApi()
        else
            Toast.makeText(activity, getString(R.string.dialog_no_network), Toast.LENGTH_SHORT).show()
    }

    abstract fun initAdapter() : BaseAdapter<T>
    abstract fun callApi()
}