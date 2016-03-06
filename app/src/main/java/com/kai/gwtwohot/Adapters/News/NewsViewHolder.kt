package com.kai.gwtwohot.Adapters.News

import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import com.kai.gwtwohot.Activities.IKaiActivity
import com.kai.gwtwohot.Adapters.BaseViewHolder
import com.kai.gwtwohot.Fragments.WebFragment
import com.kai.gwtwohot.R

/**
 * Created by ikraammoothianpillay1 on 2/20/16.
 */
class NewsViewHolder(itemView: View,_activity: IKaiActivity) : BaseViewHolder<NewsInfo>(itemView,_activity) {

    protected var vTitle: TextView
    protected var vSummary: TextView
    protected var vAuthor: TextView
    protected var vDate: TextView
    protected var vItemLayout: RelativeLayout
    protected var vlAuthor: TextView
    protected var vlDate: TextView
    private var link: String? = null

    init {
        vTitle = itemView.findViewById(R.id.txtTitle) as TextView
        vSummary = itemView.findViewById(R.id.txtSummary) as TextView
        vAuthor = itemView.findViewById(R.id.txtAuthor) as TextView
        vDate = itemView.findViewById(R.id.txtDate) as TextView
        vlAuthor = itemView.findViewById(R.id.lblAuthor) as TextView
        vlDate = itemView.findViewById(R.id.lblDate) as TextView
        vItemLayout = itemView.findViewById(R.id.itemLayout) as RelativeLayout
        vItemLayout.setOnClickListener(this)
    }

    override fun bind(item: NewsInfo) {
        this.vTitle.text = item.title
        this.vSummary.text = item.summary
        this.vAuthor.text = item.author
        this.vDate.text = item.date
        this.link = item.link
    }

    override fun onClick(view: View) {
        activity.setFragment(WebFragment.newInstance(activity,link!!,vTitle.text.toString()),true)
    }
}
