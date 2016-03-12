package com.kai.gwtwohot.Activities

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.Spinner

/**
 * Created by ikraammoothianpillay1 on 2/20/16.
 */
interface IKaiActivity {
    fun setFragment(fragment: Fragment,retainCurrentFragment: Boolean = false)
    fun getContext() : Context
    fun getActivity() : AppCompatActivity
    fun getToolbar() : Toolbar?
    fun getSpinner() : Spinner?
    fun getCoordinatorLayout() : CoordinatorLayout?
}
