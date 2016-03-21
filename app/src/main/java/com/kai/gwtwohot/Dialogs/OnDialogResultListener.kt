package com.kai.gwtwohot.Dialogs

/**
 * Created by ikraammoothianpillay1 on 3/19/16.
 */
interface OnDialogResultListener {
    abstract fun onPositiveResult(args: Map<String,String?>)
    abstract fun onNegativeResult()
}