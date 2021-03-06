package com.kai.gwtwohot.Adapters.News

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.kai.gwtwohot.Activities.IKaiActivity
import com.kai.gwtwohot.Adapters.BaseAdapter
import com.kai.gwtwohot.Adapters.BaseViewHolder
import com.kai.gwtwohot.R

/**
 * Created by ikraammoothianpillay1 on 2/20/16.
 */
class NewsAdapter(protected var activity: IKaiActivity) : BaseAdapter<NewsInfo>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.news_row, parent, false),activity)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<NewsInfo>, position: Int) {
        holder.bind(items[position])
    }

}
