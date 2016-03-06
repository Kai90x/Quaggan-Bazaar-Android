package com.kai.gwtwohot.Fragments

import android.animation.ObjectAnimator
import android.app.Fragment
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.widget.ProgressBar
import com.kai.gwtwohot.Activities.IKaiActivity
import com.kai.gwtwohot.R
import tr.xip.errorview.ErrorView

/**
 * Created by ikraammoothianpillay1 on 3/6/16.
 */

abstract class BaseFragment : Fragment() {
    protected var activity: IKaiActivity? = null

    protected fun setToolbarTitle(title: Int) {
        activity?.getToolbar()?.setTitle(title)
    }

    protected fun setToolbarTitle(title: String) {
        activity?.getToolbar()?.title = title
    }

    protected fun setToolbarSubtitleTitle(title: Int) {
        activity?.getToolbar()?.setSubtitle(title)
    }

    protected fun setToolbarSubtitleTitle(title: String) {
        activity?.getToolbar()?.subtitle = title
    }

    protected fun showEmailSnackbar(mailContent:String, message: Int = R.string.dialog_error_occured_3) {
        val snackbar = Snackbar.make(activity?.getCoordinatorLayout()!!, message, Snackbar.LENGTH_LONG)
        .setAction(R.string.dialog_ok, {
            //TODO: Send email error
            Log.e("TEST",mailContent)
        });
        snackbar.show();
    }

    protected fun ErrorView.show(message: Int,callMethod: () -> Unit,title: Int = R.string.dialog_error,showRetry: Boolean = true) {
        setTitle(title)
        setSubtitle(message)
        showRetryButton(showRetry)
        visibility = View.VISIBLE
        setOnRetryListener(object: ErrorView.RetryListener {
            override fun onRetry() {
                visibility = View.GONE
                callMethod.invoke()
            }
        })
    }

    protected fun ProgressBar.start() {
        visibility = View.VISIBLE
        val animation = ObjectAnimator.ofInt(this, "progress", 0, 500)
        animation.duration = 1000
        animation.repeatCount = Animation.INFINITE
        animation.interpolator = DecelerateInterpolator()
        animation.start ()
    }

    protected fun ProgressBar.stop() {
        visibility = View.GONE
        clearAnimation()
    }

    protected fun View.circularReveal() {
        if  (android.os.Build.VERSION.SDK_INT >= 21) {
            addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
                override fun onLayoutChange(v: View, left: Int, top: Int, right: Int, bottom: Int, oldLeft: Int, oldTop: Int,
                                            oldRight: Int, oldBottom: Int) {
                    v.removeOnLayoutChangeListener(this)

                    val cx = (left + right) / 2
                    val cy = (top + bottom) / 2
                    val finalRadius = Math.max(width, height)

                    val reveal = ViewAnimationUtils.createCircularReveal(v, cx, cy, 0f, finalRadius.toFloat())
                    reveal.interpolator = DecelerateInterpolator(2f)
                    reveal.duration = 2500
                    reveal.start()
                }
            })
        }
    }

}