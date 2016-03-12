package com.kai.gwtwohot.Adapters.Dungeons

import android.view.LayoutInflater
import android.view.ViewGroup
import com.kai.gwtwohot.Activities.IKaiActivity
import com.kai.gwtwohot.Adapters.BaseAdapter
import com.kai.gwtwohot.Adapters.BaseViewHolder
import com.kai.gwtwohot.R

/**
 * Created by ikraammoothianpillay1 on 3/12/16.
 */
class DungeonAdapter(protected var activity: IKaiActivity) : BaseAdapter<DungeonInfo>() {

    private val HEADER: Int = 0
    private val CONTENT: Int = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<DungeonInfo> {
        return if (viewType == HEADER)
            DungeonHeaderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.dungeon_header_row, parent, false), activity)
        else
            DungeonViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.dungeon_row, parent, false), activity)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<DungeonInfo>, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position].isHeader) HEADER else CONTENT
    }

}
