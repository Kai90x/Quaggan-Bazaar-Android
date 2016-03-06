package com.kai.gwtwohot.Utils

import android.app.Activity
import android.support.v7.widget.Toolbar
import android.view.Gravity
import android.view.View
import com.kai.gwtwohot.Activities.IKaiActivity
import com.kai.gwtwohot.Fragments.NewsFragment
import com.kai.gwtwohot.R
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.DividerDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem

/**
 * Created by ikraammoothianpillay1 on 2/20/16.
 */
object DrawerUtils {

    fun build(activity: Activity, toolbar: Toolbar?,kaiActivity : IKaiActivity): Drawer {

        val divider = DividerDrawerItem();

        val newsItem = PrimaryDrawerItem().withName(R.string.news).withIcon(R.drawable.gw_drawer_news)
        val eventsItem = PrimaryDrawerItem().withName(R.string.events).withIcon(R.drawable.gw_drawer_dragon)
        val legendaryItem = PrimaryDrawerItem().withName(R.string.legendarycraft).withIcon(R.drawable.gw_drawer_forge)
        val achievements = PrimaryDrawerItem().withName(R.string.achievements).withIcon(R.drawable.gw_drawer_achievement)

        val itemHeader = PrimaryDrawerItem().withName(R.string.items).withSelectable(false)
        val items = PrimaryDrawerItem().withName(R.string.items).withIcon(R.drawable.gw_drawer_swords)
        val favoriteItems = PrimaryDrawerItem().withName(R.string.favorite_items).withIcon(R.drawable.gw_drawer_favorite)
        val itemNotified = PrimaryDrawerItem().withName(R.string.notifications).withIcon(R.drawable.gw_drawer_notifications)

        val accountHeader = PrimaryDrawerItem().withName(R.string.account).withSelectable(false)
        val apiKey = PrimaryDrawerItem().withName(R.string.account_api).withIcon(R.drawable.gw_drawer_key)
        val myCharacters = PrimaryDrawerItem().withName(R.string.account_my_character).withIcon(R.drawable.gw_drawer_characters)
        val transactionHistory = PrimaryDrawerItem().withName(R.string.account_history_transactions).withIcon(R.drawable.gw_drawer_items)
        val currentTransaction = PrimaryDrawerItem().withName(R.string.account_my_current_transactions).withIcon(R.drawable.gw_drawer_items)
        val bank = PrimaryDrawerItem().withName(R.string.account_bank).withIcon(R.drawable.gw_drawer_bank)
        val material = PrimaryDrawerItem().withName(R.string.account_material).withIcon(R.drawable.gw_drawer_items)
        val pvpStats = PrimaryDrawerItem().withName(R.string.account_pvp_stats).withIcon(R.drawable.gw_drawer_items)
        val pvpGames = PrimaryDrawerItem().withName(R.string.account_pvp_games).withIcon(R.drawable.gw_drawer_items)
        val apiHelp = PrimaryDrawerItem().withName(R.string.account_api_Help).withIcon(R.drawable.gw_drawer_help)

        val quagganBazaarHeader = PrimaryDrawerItem().withName(R.string.app_name).withSelectable(false)
        val setting = PrimaryDrawerItem().withName(R.string.preference_setting).withIcon(R.drawable.gw_drawer_setting)
        val credit = PrimaryDrawerItem().withName(R.string.preference_credit).withIcon(R.drawable.gw_drawer_mustache)
        val share = PrimaryDrawerItem().withName(R.string.preference_Share).withIcon(R.drawable.gw_drawer_share)
        val email = PrimaryDrawerItem().withName(R.string.preference_email).withIcon(R.drawable.gw_drawer_email)

        val kaiHeader = PrimaryDrawerItem().withName(R.string.kai).withSelectable(false)
        val childFund = PrimaryDrawerItem().withName(R.string.child_fund).withIcon(R.drawable.gw_drawer_charity)

        val drawer =  DrawerBuilder().withActivity(activity)
        if (toolbar != null)
            drawer.withToolbar(toolbar)

        return drawer
                .withDrawerGravity(Gravity.START)
                .addDrawerItems(
                        newsItem,eventsItem,legendaryItem,achievements,
                        divider,
                        itemHeader,items,favoriteItems,itemNotified,
                        divider,
                        accountHeader,apiKey,myCharacters,transactionHistory,currentTransaction,bank,material,pvpStats,pvpGames,apiHelp,
                        divider,
                        quagganBazaarHeader,setting,credit,share,email,
                        divider,
                        kaiHeader,childFund
                    ).withOnDrawerItemClickListener { view, position, drawerItem ->
                        when(drawerItem) {
                            newsItem -> kaiActivity.setFragment(NewsFragment.newInstance(kaiActivity))
                        }

                    false
                }
                .withCloseOnClick(true)
                .build()
    }
}
