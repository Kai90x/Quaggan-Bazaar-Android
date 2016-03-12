package com.kai.gwtwohot.Utils

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.support.v4.content.ContextCompat
import java.util.*

/**
 * Created by ikraammoothianpillay1 on 2/20/16.
 */
object ColorUtils {

    fun isNetworkAvailable(context: Context) : Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        val isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting
        return isConnected;
    }

    fun isColorDark(color: Int): Boolean {
        val darkness = 1 - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)) / 255
        return (darkness > 0.5)
    }

    fun Darken(color: Int): Int {
        var color = color
        val hsv = FloatArray(3)
        Color.colorToHSV(color, hsv)
        hsv[2] *= 0.8f
        color = Color.HSVToColor(hsv)
        return color
    }

    fun changeDrawableColor(context: Context,drawable: Int,color: Int) : Drawable {
        val drawable = ContextCompat.getDrawable(context,drawable);
        drawable.setColorFilter(color, PorterDuff.Mode.SRC_IN)
        return drawable
    }

    fun randomColor() : Int {
        val rnd = Random()
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

}
