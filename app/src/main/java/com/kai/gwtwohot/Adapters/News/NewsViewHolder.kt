package com.kai.gwtwohot.Adapters.News

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.kai.gwtwohot.Adapters.BaseViewHolder
import com.kai.gwtwohot.R

/**
 * Created by ikraammoothianpillay1 on 2/20/16.
 */
class NewsViewHolder(itemView: View) : BaseViewHolder<NewsInfo>(itemView) {

    public  var context: Context
    protected var vTitle: TextView
    protected var vSummary: TextView
    protected var vAuthor: TextView
    protected var vDate: TextView
    protected var vLink: TextView
    protected var vItemLayout: RelativeLayout
    protected var vlAuthor: TextView
    protected var vlDate: TextView

    init {
        context = itemView.context
        vTitle = itemView.findViewById(R.id.txtTitle) as TextView
        vSummary = itemView.findViewById(R.id.txtSummary) as TextView
        vAuthor = itemView.findViewById(R.id.txtAuthor) as TextView
        vDate = itemView.findViewById(R.id.txtDate) as TextView
        vLink = itemView.findViewById(R.id.txtlink) as TextView
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
        this.vLink.text = item.link
    }

    override fun onClick(view: View) {
        /*val link = (view.findViewById(R.id.txtlink) as TextView).text.toString()

        val intent = Intent(context, BrowserActivity::class.java)
        val bundle = Bundle()
        bundle.putString(Helper.BUNDLE_LINK, link)
        intent.putExtras(bundle)

        context.startActivity(intent)*/
    }
}
