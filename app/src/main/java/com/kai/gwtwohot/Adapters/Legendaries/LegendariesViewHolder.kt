package com.kai.gwtwohot.Adapters.Legendaries

import android.graphics.Color
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import com.kai.gwtwohot.Activities.IKaiActivity
import com.kai.gwtwohot.Adapters.BaseViewHolder
import com.kai.gwtwohot.Adapters.News.NewsInfo
import com.kai.gwtwohot.Extensions.setGuildWarsItemRarityColor
import com.kai.gwtwohot.Extensions.shorten
import com.kai.gwtwohot.Fragments.WebFragment
import com.kai.gwtwohot.R
import com.kai.gwtwohot.Utils.ColorUtils
import com.makeramen.roundedimageview.RoundedImageView
import com.squareup.picasso.Picasso

/**
 * Created by ikraammoothianpillay1 on 3/12/16.
 */
class LegendariesViewHolder(itemView: View,kaiActivity: IKaiActivity) : BaseViewHolder<LegendariesInfo>(itemView,kaiActivity) {

    protected var vImage: RoundedImageView
    protected var vName: TextView
    protected var vItemLayout: RelativeLayout
    protected var itemId: String? = null

    init {
        vImage = itemView.findViewById(R.id.imgItem) as RoundedImageView
        vName = itemView.findViewById(R.id.txtItemName) as TextView
        vItemLayout = itemView.findViewById(R.id.itemLayout) as RelativeLayout
        vItemLayout.setOnClickListener(this)
    }

    override fun bind(item: LegendariesInfo) {
        this.vImage.setGuildWarsItemRarityColor(item.rarity)
        this.vName.text = item.name?.shorten()
        this.itemId = item.id

        Picasso.with(kaiActivity.getContext()).load(item.iconUrl).into(this.vImage)
    }

    override fun onClick(view: View) {

    }
}
