package com.kai.gwtwohot.Activities

import android.app.Fragment
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.RelativeLayout
import android.widget.Toast
import com.kai.gwtwohot.R
import com.kai.gwtwohot.Utils.DrawerUtils

class MainActivity : AppCompatActivity(), IActivity {

    private var doubleBackToExitPressedOnce: Boolean = false
    private var pref: SharedPreferences? = null
    private var myToolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pref = PreferenceManager.getDefaultSharedPreferences(this)
        if (savedInstanceState == null) {
        }

        myToolbar = findViewById(R.id.tool_bar) as Toolbar
        if (myToolbar != null) {
            setSupportActionBar(myToolbar)
        }

        DrawerUtils.build(this,myToolbar,this)
    }

    public override fun setFragment(fragment: Fragment) {
        fragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }

    override fun onResume() {
        super.onResume();
        this.doubleBackToExitPressedOnce = false;
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press twice to exit", Toast.LENGTH_SHORT).show();
    }

}
