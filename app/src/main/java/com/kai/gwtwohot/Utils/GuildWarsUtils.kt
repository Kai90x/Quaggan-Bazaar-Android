package com.kai.gwtwohot.Utils

import com.kai.gwtwohot.R

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

}