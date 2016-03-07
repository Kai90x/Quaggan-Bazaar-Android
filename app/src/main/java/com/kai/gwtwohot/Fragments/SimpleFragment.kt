package com.kai.gwtwohot.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kai.gwtwohot.Extensions.circularReveal
import com.kai.gwtwohot.R

/**
 * Created by ikraammoothianpillay1 on 3/7/16.
 */
class SimpleFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(arguments.getInt(LAYOUT), container, false)
        rootView.circularReveal()
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setToolbarTitle(arguments.getInt(TITLE))
    }

    companion object {
        val LAYOUT: String = "layout"
        val TITLE: String = "title"

        fun newInstance(layoutId: Int,title: Int) : SimpleFragment {
            val frag = SimpleFragment()

            var bundle = Bundle()
            bundle.putInt(LAYOUT,layoutId)
            bundle.putInt(TITLE,title)
            frag.arguments = bundle

            return frag
        }
    }
}