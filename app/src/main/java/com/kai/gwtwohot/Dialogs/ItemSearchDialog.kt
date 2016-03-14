package com.kai.gwtwohot.Dialogs

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.kai.gwtwohot.Fragments.SimpleFragment
import com.kai.gwtwohot.R

/**
 * Created by ikraammoothianpillay1 on 3/13/16.
 */
class ItemSearchDialog : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_search, container, false)
        dialog.setTitle(R.string.search)
        (rootView.findViewById(R.id.btnSearch ) as Button?)?.setOnClickListener({
            dialog.cancel()
        })
        (rootView.findViewById(R.id.btnCancel ) as Button?)?.setOnClickListener({
            dialog.cancel()
        })
        return rootView
    }

    companion object {
        fun newInstance() : ItemSearchDialog = ItemSearchDialog()
    }
}