package com.kai.gwtwohot.Adapters

import android.view.View

import com.malinskiy.superrecyclerview.swipe.BaseSwipeAdapter.BaseSwipeableViewHolder

/**
 * Created by ikraammoothianpillay1 on 2/20/16.
 */
abstract class BaseViewHolder<T>(itemView: View) : BaseSwipeableViewHolder(itemView), View.OnClickListener {
    abstract fun bind(item: T)
}
