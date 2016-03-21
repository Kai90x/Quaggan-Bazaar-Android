package com.kai.gwtwohot.Utils

import android.content.Context
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ImageSpan
import com.kai.gwtwohot.Dungeon
import com.kai.gwtwohot.R
import java.util.*

/**
 * Created by ikraammoothianpillay1 on 3/6/16.
 */
object GuildWarsUtils {

    fun getPVPRank(rank: Int): Int {
        if (rank < 10)
            return R.drawable.rabbit
        else if (rank >= 10 && rank < 20)
            return R.drawable.deer
        else if (rank >= 20 && rank < 30)
            return R.drawable.dolyak
        else if (rank >= 30 && rank < 40)
            return R.drawable.wolf
        else if (rank >= 40 && rank < 50)
            return R.drawable.tiger
        else if (rank >= 50 && rank < 60)
            return R.drawable.bear
        else if (rank >= 60 && rank < 70)
            return R.drawable.shark
        else if (rank >= 70 && rank < 80)
            return R.drawable.phoenix
        else
            return R.drawable.dragon
    }

    fun getProfessionResource(profession: Int): Int {
        when (profession) {
            R.string.profession_elemantalist -> return R.drawable.elementalist_icon
            R.string.profession_engineer -> return R.drawable.engineer_icon
            R.string.profession_guardian -> return R.drawable.guardian_icon
            R.string.profession_mesmer -> return R.drawable.mesmer_icon
            R.string.profession_necromancer -> return R.drawable.necromancer_icon
            R.string.profession_ranger -> return R.drawable.ranger_icon
            R.string.profession_thief -> return R.drawable.thief_icon
            R.string.profession_warrior -> return R.drawable.warrior_icon
            else -> return R.drawable.abc_list_selector_holo_dark
        }
    }

    fun getFormattedPrice(price: String): String {
        val totalPrice = Integer.parseInt(price)

        val gold = if (totalPrice / 10000 > 0) Integer.toString((totalPrice / 10000).toInt()) + " {g} " else ""

        val silverPrice = totalPrice % 10000
        val silver = if (silverPrice / 100 > 0) Integer.toString((silverPrice / 100).toInt()) + " {s} " else ""

        val copperPrice = silverPrice % 100
        val copper = Integer.toString(copperPrice) + " {c} "

        return gold + silver + copper
    }

    fun getTokenFormattedText(dungeon: Dungeon): String? {
        val dungeonName = dungeon.dungeon.toLowerCase()

        if (dungeonName.contains("ascalonian") && dungeonName.contains("ascalonian"))
            return dungeon.tokenreward + " {ascalonian}"

        if (dungeonName.contains("caudecus") && dungeonName.contains("manor"))
            return dungeon.tokenreward + " {caudecus}"

        if (dungeonName.contains("twilight") && dungeonName.contains("arbor"))
            return dungeon.tokenreward + " {twilight}"

        if (dungeonName.contains("sorrow") && dungeonName.contains("embrace"))
            return dungeon.tokenreward + " {sorrow}"

        if (dungeonName.contains("citadel") && dungeonName.contains("flame"))
            return dungeon.tokenreward + " {citadel}"

        if (dungeonName.contains("honor") && dungeonName.contains("waves"))
            return dungeon.tokenreward + " {honor}"

        if (dungeonName.contains("crucible") && dungeonName.contains("eternity"))
            return dungeon.tokenreward + " {crucible}"

        if (dungeonName.contains("ruined") && dungeonName.contains("arah"))
            return dungeon.tokenreward + " {ruinedcity}"

        if (dungeonName.contains("fractals") && dungeonName.contains("mists"))
            return dungeon.tokenreward + " {fractals}"

        return null
    }

    fun getRarityColor(context: Context, rarity: String): Int {
        when(rarity) {
            context.getString(R.string.junk)-> return R.color.junk
            context.getString(R.string.basic)-> return R.color.basic
            context.getString(R.string.fine)-> return R.color.fine
            context.getString(R.string.masterwork)-> return R.color.masterwork
            context.getString(R.string.rare)-> return R.color.rare
            context.getString(R.string.exotic)-> return R.color.exotic
            context.getString(R.string.ascended)-> return R.color.ascended
            context.getString(R.string.lengendary)-> return R.color.legendary
        }
        return 0
    }

    fun getItemSubtype(context: Context, itemType: String): Array<String>? {
        val res = context.resources

        if (itemType == context.getString(R.string.item_armor))
            return res.getStringArray(R.array.subtype_armor_array)
        if (itemType == context.getString(R.string.item_consumable))
            return res.getStringArray(R.array.subtype_consumable_array)
        if (itemType == context.getString(R.string.item_container))
            return res.getStringArray(R.array.subtype_container_array)
        if (itemType == context.getString(R.string.item_gathering))
            return res.getStringArray(R.array.subtype_gatheringtool_array)
        if (itemType == context.getString(R.string.item_gizmo))
            return res.getStringArray(R.array.subtype_gizmo_array)
        if (itemType == context.getString(R.string.item_trinket))
            return res.getStringArray(R.array.subtype_trinket_array)
        if (itemType == context.getString(R.string.item_upgrade_component))
            return res.getStringArray(R.array.subtype_upgradecomponent_array)
        if (itemType == context.getString(R.string.item_weapon))
            return res.getStringArray(R.array.subtype_weapon_array)

        return null
    }


}