package com.kai.gwtwohot.Fragments

import android.app.Fragment
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log
import com.kai.gwtwohot.Activities.IKaiActivity
import com.kai.gwtwohot.Activities.MainActivity
import com.kai.gwtwohot.R

/**
 * Created by ikraammoothianpillay1 on 3/6/16.
 */

abstract class BaseFragment : Fragment() {
    protected var kaiActivity: IKaiActivity? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        kaiActivity = if (activity is MainActivity) this.activity as MainActivity else null
    }

    protected fun setToolbarTitle(title: Int) {
        kaiActivity?.getToolbar()?.setTitle(title)
    }

    protected fun setToolbarTitle(title: String) {
        kaiActivity?.getToolbar()?.title = title
    }

    protected fun setToolbarSubtitleTitle(title: Int) {
        kaiActivity?.getToolbar()?.setSubtitle(title)
    }

    protected fun setToolbarSubtitleTitle(title: String) {
        kaiActivity?.getToolbar()?.subtitle = title
    }

    protected fun showEmailSnackbar(mailContent:String, message: Int = R.string.dialog_error_occured_3) {
        val snackbar = Snackbar.make(kaiActivity?.getCoordinatorLayout()!!, message, Snackbar.LENGTH_LONG)
        .setAction(R.string.dialog_ok, {
            //TODO: Send email error
            Log.e("TEST",mailContent)
        });
        snackbar.show();
    }

}