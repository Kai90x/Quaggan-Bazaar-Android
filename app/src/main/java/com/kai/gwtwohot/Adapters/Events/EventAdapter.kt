package com.kai.gwtwohot.Adapters.Events

import android.view.LayoutInflater
import android.view.ViewGroup
import com.kai.gwtwohot.Activities.IKaiActivity
import com.kai.gwtwohot.Adapters.BaseAdapter
import com.kai.gwtwohot.Adapters.BaseViewHolder
import com.kai.gwtwohot.R

/**
 * Created by ikraammoothianpillay1 on 3/7/16.
 */
class EventAdapter(protected var activity: IKaiActivity) : BaseAdapter<EventInfo>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return EventViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.event_row, parent, false), activity)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<EventInfo>, position: Int) {
        holder.bind(items[position])
    }

}
