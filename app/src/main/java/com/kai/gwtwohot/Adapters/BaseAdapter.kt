package com.kai.gwtwohot.Adapters

import android.util.Log
import android.view.ViewGroup
import com.kai.gwtwohot.Adapters.BaseViewHolder
import com.kai.gwtwohot.Adapters.News.NewsInfo
import com.malinskiy.superrecyclerview.swipe.BaseSwipeAdapter
import java.util.*

/**
 * Created by ikraammoothianpillay1 on 2/20/16.
 */
abstract class BaseAdapter<T>()
: BaseSwipeAdapter<BaseViewHolder<T>>() {
    protected var items: MutableList<T> = ArrayList<T>()

    fun add(item: T) {
        this.items.add(item)
        notifyItemInserted(items.size - 1)
    }

    fun add(items: List<T>) {
        this.items.addAll(items)
        notifyItemInserted(this.items.size - 1)
    }

    fun update(position: Int,newItem: T) {
        this.items[position] = newItem
        notifyItemInserted(items.size - 1)
    }

    fun remove(item: T) {
        this.items.remove(item)
        notifyItemInserted(items.size - 1)
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    fun isEmpty(): Boolean = items.size == 0

    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T>
    abstract override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int)
}
