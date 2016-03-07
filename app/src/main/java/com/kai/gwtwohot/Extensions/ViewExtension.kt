package com.kai.gwtwohot.Extensions

import android.animation.ObjectAnimator
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.widget.ProgressBar
import com.kai.gwtwohot.R
import tr.xip.errorview.ErrorView

/**
 * Created by ikraammoothianpillay1 on 3/7/16.
 */

fun ErrorView.show(message: Int, callMethod: () -> Unit, title: Int = R.string.dialog_error, showRetry: Boolean = true) {
    setTitle(title)
    setSubtitle(message)
    showRetryButton(showRetry)
    visibility = View.VISIBLE
    setOnRetryListener(object : ErrorView.RetryListener {
        override fun onRetry() {
            visibility = View.GONE
            callMethod.invoke()
        }
    })
}

fun ProgressBar.start() {
    visibility = View.VISIBLE
    val animation = ObjectAnimator.ofInt(this, "progress", 0, 500)
    animation.duration = 1000
    animation.repeatCount = Animation.INFINITE
    animation.interpolator = DecelerateInterpolator()
    animation.start ()
}

fun ProgressBar.stop() {
    visibility = View.GONE
    clearAnimation()
}

fun View.circularReveal(cx: Int = ((left + right) / 2),cy: Int = ((top + bottom) / 2)) {
    if (android.os.Build.VERSION.SDK_INT >= 21) {
        addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
            override fun onLayoutChange(v: View, left: Int, top: Int, right: Int, bottom: Int, oldLeft: Int, oldTop: Int,
                                        oldRight: Int, oldBottom: Int) {
                v.removeOnLayoutChangeListener(this)
                val finalRadius = Math.hypot(v.width.toDouble(), v.height.toDouble())

                val reveal = ViewAnimationUtils.createCircularReveal(v, cx, cy, 0f, finalRadius.toFloat())
                reveal.interpolator = DecelerateInterpolator(2f)
                reveal.duration = 2500
                reveal.start()
            }
        })
    }
}
