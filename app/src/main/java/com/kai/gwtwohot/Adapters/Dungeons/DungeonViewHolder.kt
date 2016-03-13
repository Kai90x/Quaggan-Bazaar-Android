package com.kai.gwtwohot.Adapters.Dungeons

import android.view.View
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.RelativeLayout
import android.widget.TextView
import com.kai.gwtwohot.Activities.IKaiActivity
import com.kai.gwtwohot.Adapters.BaseViewHolder
import com.kai.gwtwohot.DaoRepositories.DungeonRepository
import com.kai.gwtwohot.Dungeon
import com.kai.gwtwohot.Extensions.getSmiley
import com.kai.gwtwohot.Extensions.shorten
import com.kai.gwtwohot.R
import com.kai.gwtwohot.Utils.GuildWarsUtils

/**
 * Created by ikraammoothianpillay1 on 3/12/16.
 */
class DungeonViewHolder(itemView: View,kaiActivity: IKaiActivity) : BaseViewHolder<DungeonInfo>(itemView,kaiActivity) {
    protected var vPath: TextView
    protected var vTokenReward: TextView
    protected var vGoldReward: TextView
    protected var chkDungeon: CheckBox
    protected var vItemLayout: RelativeLayout
    protected var dungeon: Dungeon? = null

    init {
        vPath = itemView.findViewById(R.id.txtPath) as TextView
        vTokenReward = itemView.findViewById(R.id.txtTokenReward) as TextView
        vGoldReward = itemView.findViewById(R.id.txtGoldReward) as TextView
        chkDungeon = itemView.findViewById(R.id.chkDungeon) as CheckBox
        vItemLayout = itemView.findViewById(R.id.itemLayout) as RelativeLayout
    }

    override fun bind(item: DungeonInfo) {
        this.vPath.text = item.dungeon.path.shorten(18)
        this.vTokenReward.text = GuildWarsUtils.getTokenFormattedText(item.dungeon)
        this.vGoldReward.text = GuildWarsUtils.getFormattedPrice(item.dungeon.goldreward)

        this.vTokenReward.getSmiley()
        this.vGoldReward.getSmiley()

        this.vItemLayout.setOnClickListener(null)
        this.chkDungeon.setOnCheckedChangeListener(null)

        this.chkDungeon.isChecked = item.dungeon.isSelected
        this.dungeon = item.dungeon

        this.chkDungeon.setOnCheckedChangeListener({ p0, p1 ->
            item.dungeon.isSelected = p1
            DungeonRepository.insertOrUpdate(kaiActivity.getContext(),item.dungeon)
        })
        vItemLayout.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        var id = view.id
        when (id) {
            vItemLayout.id -> {
                dungeon?.isSelected = dungeon?.isSelected?.not()!!
                DungeonRepository.insertOrUpdate(kaiActivity.getContext(),dungeon!!)
                this.chkDungeon.isChecked = dungeon?.isSelected!!
            }
        }

    }
}
