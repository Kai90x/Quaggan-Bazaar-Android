package com.kai.gwtwohot.Dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.kai.gwtwohot.Extensions.circularReveal
import com.kai.gwtwohot.Fragments.SimpleFragment

import com.kai.gwtwohot.R

/**
 * Created by ikraammoothianpillay1 on 3/7/16.
 */
class ApiHelpDialog : DialogFragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(arguments.getInt(SimpleFragment.LAYOUT), container, false)
        dialog.setTitle(arguments.getInt(SimpleFragment.TITLE))
        (rootView.findViewById(R.id.btnOk) as Button?)?.setOnClickListener({
            dialog.cancel()
        })
        return rootView
    }

    companion object {
        val LAYOUT: String = "layout"
        val TITLE: String = "title"

        fun newInstance(layoutId: Int,title: Int) : ApiHelpDialog {
            val frag = ApiHelpDialog()

            var bundle = Bundle()
            bundle.putInt(LAYOUT,layoutId)
            bundle.putInt(TITLE,title)
            frag.arguments = bundle

            return frag
        }
    }
}
