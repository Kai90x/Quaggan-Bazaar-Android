package com.kai.gwtwohot.Adapters.Items

import android.view.LayoutInflater
import android.view.ViewGroup
import com.kai.gwtwohot.Activities.IKaiActivity
import com.kai.gwtwohot.Adapters.BaseAdapter
import com.kai.gwtwohot.Adapters.BaseViewHolder
import com.kai.gwtwohot.R

/**
 * Created by ikraammoothianpillay1 on 3/13/16.
 */
class ItemAdapter(protected var activity: IKaiActivity) : BaseAdapter<ItemInfo>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ItemInfo> {
        return ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false), activity)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ItemInfo>, position: Int) {
        holder.bind(items[position])
    }

}
