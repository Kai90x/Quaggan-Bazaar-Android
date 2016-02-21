package com.kai.gwtwohot.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kai.gwtwohot.R
import com.malinskiy.superrecyclerview.SuperRecyclerView

abstract class BaseFeedFragment : Fragment() {
    protected var recyclerList: SuperRecyclerView? = null
    protected var batchSize: Int = 0
    protected var currentpage: Int = 1
    protected var totalBatches: Int = 0

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_list, container, false)
        recyclerList = rootView.findViewById(R.id.fragment_list) as SuperRecyclerView
        initRecycler()
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    private fun initRecycler() {
        if (recyclerList != null) {
            val layout = GridLayoutManager(activity,1)
            recyclerList?.setLayoutManager(layout)
        }
    }

    protected fun stopRecyclerViewLoad() {
        if (recyclerList!!.isLoadingMore)
            recyclerList!!.hideMoreProgress()
    }
}