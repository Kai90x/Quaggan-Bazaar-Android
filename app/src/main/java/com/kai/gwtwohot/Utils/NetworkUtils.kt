package com.kai.gwtwohot.Utils

import android.content.Context
import android.net.ConnectivityManager
/**
 * Created by ikraammoothianpillay1 on 2/20/16.
 */

object NetworkUtils {

    fun isNetworkAvailable(context: Context) : Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        val isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting
        return isConnected;
    }

}
