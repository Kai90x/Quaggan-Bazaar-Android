package com.kai.gwtwohot.Utils

import android.app.Activity
import android.support.v7.widget.Toolbar
import android.view.View
import com.kai.gwtwohot.Activities.IActivity
import com.kai.gwtwohot.R
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.DividerDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem

/**
 * Created by ikraammoothianpillay1 on 2/20/16.
 */
object DrawerUtils {

    fun build(activity: Activity, toolbar: Toolbar?,delegate : IActivity): Drawer {
        val newsItem = PrimaryDrawerItem().withName(R.string.news).withIcon(R.drawable.ic_action_news)
        val eventsItem = PrimaryDrawerItem().withName(R.string.events).withIcon(R.drawable.ic_action_knight)
        val legendaryItem = PrimaryDrawerItem().withName(R.string.legendarycraft).withIcon(R.drawable.ic_action_knight)
        val achievements = PrimaryDrawerItem().withName(R.string.achievements).withIcon(R.drawable.trophy)

        //SecondaryDrawerItem item2 = new SecondaryDrawerItem().withName(R.string.drawer_item_settings);

        val drawer =  DrawerBuilder().withActivity(activity)
        if (toolbar != null)
            drawer.withToolbar(toolbar)

        return drawer.addDrawerItems(
                newsItem, eventsItem, legendaryItem, achievements,
                DividerDrawerItem()).withOnDrawerItemClickListener { view, position, drawerItem ->
            // do something with the clicked item :D
            true
        }.build()
    }
}
