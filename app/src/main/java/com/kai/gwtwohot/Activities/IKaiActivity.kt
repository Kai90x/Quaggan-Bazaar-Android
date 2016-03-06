package com.kai.gwtwohot.Activities

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.v7.widget.Toolbar

/**
 * Created by ikraammoothianpillay1 on 2/20/16.
 */
interface IKaiActivity {
    fun setFragment(fragment: Fragment,retainCurrentFragment: Boolean = false)
    fun getContext() : Context
    fun getToolbar() : Toolbar?
    fun getCoordinatorLayout() : CoordinatorLayout?
}
