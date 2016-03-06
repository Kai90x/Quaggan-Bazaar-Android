package com.kai.gwtwohot.Adapters

import android.content.Context
import android.view.View
import com.kai.gwtwohot.Activities.IKaiActivity

import com.malinskiy.superrecyclerview.swipe.BaseSwipeAdapter.BaseSwipeableViewHolder

/**
 * Created by ikraammoothianpillay1 on 2/20/16.
 */
abstract class BaseViewHolder<T>(itemView: View,activity: IKaiActivity) : BaseSwipeableViewHolder(itemView), View.OnClickListener {
    var activity: IKaiActivity

    init {
        this.activity = activity
    }

    abstract fun bind(item: T)
}
