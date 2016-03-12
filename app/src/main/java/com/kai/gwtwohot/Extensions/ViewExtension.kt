package com.kai.gwtwohot.Extensions

import android.animation.ObjectAnimator
import android.content.Context
import android.support.v4.content.ContextCompat
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ImageSpan
import android.util.Log
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.widget.ProgressBar
import android.widget.TextView
import com.github.clans.fab.FloatingActionButton
import com.github.clans.fab.FloatingActionMenu
import com.kai.gwtwohot.R
import com.kai.gwtwohot.Utils.ExceptionUtils
import com.kai.gwtwohot.Utils.GuildWarsUtils
import com.makeramen.roundedimageview.RoundedImageView
import tr.xip.errorview.ErrorView
import java.util.*

/**
 * Created by ikraammoothianpillay1 on 3/7/16.
 */

fun ErrorView.show(message: Int, callMethod: () -> Unit, title: Int = R.string.dialog_error, showRetry: Boolean = true) {
    setTitle(title)
    setSubtitle(message)
    showRetryButton(showRetry)
    visibility = View.VISIBLE
    setOnRetryListener({
        visibility = View.GONE
        callMethod.invoke()
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

fun FloatingActionMenu.addMenu(labelText: String,imageResource: Int,buttonSize: Int = FloatingActionButton.SIZE_MINI) {
    val menu = FloatingActionButton(context)
    menu.buttonSize = buttonSize
    menu.labelText = labelText
    menu.setImageResource(imageResource)
    this.addMenuButton(menu)
}

fun RoundedImageView.setGuildWarsItemRarityColor(rarity: String?) {
    if (rarity != null) {
        val color = GuildWarsUtils.getRarityColor(context, rarity)
        if (color != 0)
            borderColor = ContextCompat.getColor(context, color)
    }
}

fun TextView.getSmiley() {
        var index: Int
        val builder = SpannableStringBuilder()
        builder.append(this.text.toString())
        val emoticons = emoticons

        for (entry in emoticons.entries) {
            try {
                val length = entry.key.length
                index = this.text.toString().indexOf(entry.key)
                while (index >= 0) {
                    println(index)
                    builder.setSpan(ImageSpan(context, entry.value), index, index + length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                    index = this.text.toString().indexOf(entry.key, index + 1)
                }
            } catch (e: Exception) {
                Log.e("TextView Smiley",ExceptionUtils.getStackTrace(e))
            }
        }
        this.text = builder
}

private val emoticons: Map<String, Int>
    get() {
        val emoticons = HashMap<String, Int>()
        emoticons.put("{s}", R.drawable.silver)
        emoticons.put("{g}", R.drawable.gold)
        emoticons.put("{c}", R.drawable.copper)

        emoticons.put("{ascalonian}", R.drawable.ascalonian_tear)
        emoticons.put("{caudecus}", R.drawable.seal_of_beetletun)
        emoticons.put("{twilight}", R.drawable.deadly_bloom)
        emoticons.put("{sorrow}", R.drawable.manifesto_of_the_moletariate)
        emoticons.put("{citadel}", R.drawable.flame_legion_charr_carving)
        emoticons.put("{honor}", R.drawable.symbol_of_koda)
        emoticons.put("{crucible}", R.drawable.knowledge_crystal)
        emoticons.put("{ruinedcity}", R.drawable.shard_of_zhaitan)
        emoticons.put("{fractals}", R.drawable.fractal_relic)

        return emoticons
    }
