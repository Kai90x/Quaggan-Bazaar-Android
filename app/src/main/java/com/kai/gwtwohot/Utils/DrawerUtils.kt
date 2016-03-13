package com.kai.gwtwohot.Utils

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.Gravity
import com.kai.gwtwohot.Activities.IKaiActivity
import com.kai.gwtwohot.Dialogs.DonationDialog
import com.kai.gwtwohot.Dialogs.ApiHelpDialog
import com.kai.gwtwohot.Fragments.*
import com.kai.gwtwohot.R
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.DividerDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem

/**
 * Created by ikraammoothianpillay1 on 2/20/16.
 */
object DrawerUtils {

    fun build(kaiActivity : IKaiActivity): Drawer {

        val divider = DividerDrawerItem();

        val newsItem = PrimaryDrawerItem().withName(R.string.news).withIcon(R.drawable.gw_drawer_news)
        val eventsItem = PrimaryDrawerItem().withName(R.string.events).withIcon(R.drawable.gw_drawer_dragon)
        val legendaryItem = PrimaryDrawerItem().withName(R.string.legendarycraft).withIcon(R.drawable.gw_drawer_forge)
        val achievements = PrimaryDrawerItem().withName(R.string.achievements).withIcon(R.drawable.gw_drawer_achievement)
        val dungeons = PrimaryDrawerItem().withName(R.string.dungeons).withIcon(R.drawable.gw_drawer_dungeons)

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
        val apiHelp = PrimaryDrawerItem().withName(R.string.account_api_Help).withIcon(R.drawable.gw_drawer_help).withSelectable(false)

        val quagganBazaarHeader = PrimaryDrawerItem().withName(R.string.app_name).withSelectable(false)
        val setting = PrimaryDrawerItem().withName(R.string.preference_setting).withIcon(R.drawable.gw_drawer_setting)
        val credit = PrimaryDrawerItem().withName(R.string.preference_credit).withIcon(R.drawable.gw_drawer_mustache)
        val share = PrimaryDrawerItem().withName(R.string.preference_Share).withIcon(R.drawable.gw_drawer_share).withSelectable(false)
        val email = PrimaryDrawerItem().withName(R.string.preference_email).withIcon(R.drawable.gw_drawer_email)

        val kaiHeader = PrimaryDrawerItem().withName(R.string.kai).withSelectable(false)
        val childFund = PrimaryDrawerItem().withName(R.string.child_fund).withIcon(R.drawable.gw_drawer_charity).withSelectable(false)

        val drawer =  DrawerBuilder().withActivity(kaiActivity.getActivity())
        if (kaiActivity.getToolbar() != null)
            drawer.withToolbar(kaiActivity.getToolbar()!!)

        return drawer
                .withDrawerGravity(Gravity.START)
                .addDrawerItems(
                        newsItem,eventsItem,legendaryItem,achievements,dungeons,
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
                            newsItem -> kaiActivity.setFragment(NewsFragment.newInstance())
                            eventsItem -> kaiActivity.setFragment(EventsFragment.newInstance())
                            dungeons -> kaiActivity.setFragment(DungeonsFragment.newInstance())
                            legendaryItem -> kaiActivity.setFragment(LegendariesFragment.newInstance())
                            items -> kaiActivity.setFragment(ItemsFragment.newInstance())
                            apiHelp -> ApiHelpDialog.newInstance(R.layout.fragment_api_key_help,R.string.account_api_Help).show(kaiActivity.getActivity().supportFragmentManager, null)
                            email -> kaiActivity.setFragment(EmailFragment.newInstance())
                            share -> shareApp(kaiActivity.getContext())
                            childFund -> DonationDialog.newInstance().show(kaiActivity.getActivity().supportFragmentManager, null)
                        }

                    false
                }
                .withCloseOnClick(true)
                .build()
    }

    private fun shareApp(context: Context) {
        try {
            val i = Intent(Intent.ACTION_SEND)
            i.type = "text/plain"
            i.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.app_name))
            var sAux = context.getString(R.string.recommend_text)
            sAux += context.getString(R.string.google_play_link)
            i.putExtra(Intent.EXTRA_TEXT, sAux)
            context.startActivity(Intent.createChooser(i, context.getString(R.string.choose_one)))
        } catch (e: Exception) {
            Log.e("Sharing",ExceptionUtils.getStackTrace(e))
        }
    }

}
