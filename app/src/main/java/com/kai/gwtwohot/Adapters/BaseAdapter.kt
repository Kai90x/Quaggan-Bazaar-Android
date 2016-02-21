package com.kai.gwtwohot.Adapters

import android.view.ViewGroup
import com.kai.gwtwohot.Adapters.BaseViewHolder
import com.malinskiy.superrecyclerview.swipe.BaseSwipeAdapter

/**
 * Created by ikraammoothianpillay1 on 2/20/16.
 */
abstract class BaseAdapter<T>(protected var items: MutableList<T>)
: BaseSwipeAdapter<BaseViewHolder<T>>() {

    fun add(item: T) {
        this.items.add(item)
        notifyItemInserted(items.size - 1)
    }

    fun add(items: List<T>) {
        this.items.addAll(items)
        notifyItemInserted(this.items.size - 1)
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size;
    }

    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T>
    abstract override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int)
}
