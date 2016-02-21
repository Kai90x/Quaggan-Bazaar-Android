package com.kai.gwtwohot.Adapters.News

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.kai.gwtwohot.Adapters.BaseAdapter
import com.kai.gwtwohot.Adapters.BaseViewHolder
import com.kai.gwtwohot.R

/**
 * Created by ikraammoothianpillay1 on 2/20/16.
 */
class NewsAdapter(protected var context: Context) : BaseAdapter<NewsInfo>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(itemView)
    }

    /*override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = newsInfoList!![position]
        holder.vTitle.text = news.title
        holder.vSummary.text = news.summary
        holder.vAuthor.text = news.author
        holder.vDate.text = news.date
        holder.vLink.text = news.link
    }*/

    override fun onBindViewHolder(holder: BaseViewHolder<NewsInfo>, position: Int) {
        holder.bind(items.get(position))
    }

}
