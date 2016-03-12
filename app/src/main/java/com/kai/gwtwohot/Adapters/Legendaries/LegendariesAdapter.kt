package com.kai.gwtwohot.Adapters.Legendaries

import android.view.LayoutInflater
import android.view.ViewGroup
import com.kai.gwtwohot.Activities.IKaiActivity
import com.kai.gwtwohot.Adapters.BaseAdapter
import com.kai.gwtwohot.Adapters.BaseViewHolder
import com.kai.gwtwohot.R

/**
 * Created by ikraammoothianpillay1 on 3/12/16.
 */
class LegendariesAdapter(protected var activity: IKaiActivity) : BaseAdapter<LegendariesInfo>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LegendariesViewHolder {
        return LegendariesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.legendary_row, parent, false), activity)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<LegendariesInfo>, position: Int) {
        holder.bind(items[position])
    }

}
