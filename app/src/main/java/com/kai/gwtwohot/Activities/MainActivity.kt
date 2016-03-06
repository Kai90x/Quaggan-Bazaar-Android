package com.kai.gwtwohot.Activities

import android.app.Fragment
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.design.widget.CoordinatorLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.Toast
import com.kai.gwtwohot.Fragments.NewsFragment
import com.kai.gwtwohot.R
import com.kai.gwtwohot.Utils.DrawerUtils

class MainActivity : AppCompatActivity(), IKaiActivity {

    private var doubleBackToExitPressedOnce: Boolean = false
    private var pref: SharedPreferences? = null
    private var myToolbar: Toolbar? = null
    private var coordinatorLayout: CoordinatorLayout? = null
    private var previousFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pref = PreferenceManager.getDefaultSharedPreferences(this)

        coordinatorLayout = findViewById(R.id.coordinatorLayout) as CoordinatorLayout
        myToolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(myToolbar)

        DrawerUtils.build(this,myToolbar,this)

        if (savedInstanceState == null) {
            setFragment(NewsFragment.Companion.newInstance(this))
            supportActionBar?.setTitle(R.string.news)
        }
    }

    override fun setFragment(fragment: Fragment,retainCurrentFragment: Boolean) {
        myToolbar?.subtitle = null
        previousFragment = if (retainCurrentFragment)  fragmentManager.findFragmentById(R.id.container) else null
        fragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }

    override fun getContext() : Context {
        return this
    }

    override fun getToolbar() : Toolbar? {
        return myToolbar
    }

    override fun getCoordinatorLayout() : CoordinatorLayout? {
        return coordinatorLayout
    }

    override fun onResume() {
        super.onResume();
        this.doubleBackToExitPressedOnce = false;
    }

    override fun onBackPressed() {
        if (previousFragment == null) {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Press twice to exit", Toast.LENGTH_SHORT).show()
        } else {
            setFragment(previousFragment!!)
        }
    }

}
