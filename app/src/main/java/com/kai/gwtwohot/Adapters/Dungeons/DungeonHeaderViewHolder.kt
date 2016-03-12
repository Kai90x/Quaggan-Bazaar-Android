package com.kai.gwtwohot.Adapters.Dungeons

import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import com.kai.gwtwohot.Activities.IKaiActivity
import com.kai.gwtwohot.Adapters.BaseViewHolder
import com.kai.gwtwohot.R

/**
 * Created by ikraammoothianpillay1 on 3/12/16.
 */
class DungeonHeaderViewHolder(itemView: View,kaiActivity: IKaiActivity) : BaseViewHolder<DungeonInfo>(itemView,kaiActivity) {
    protected var vDungeon: TextView
    protected var vItemLayout: RelativeLayout

    init {
        vDungeon = itemView.findViewById(R.id.txtDungeonName) as TextView
        vItemLayout = itemView.findViewById(R.id.itemLayout) as RelativeLayout
        vItemLayout.setOnClickListener(this)
    }

    override fun bind(item: DungeonInfo) {
        this.vDungeon.text = item.dungeon.dungeon
    }

    override fun onClick(view: View) {

    }
}