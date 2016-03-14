package com.kai.gwtwohot.Adapters.Items

import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.kai.gwtwohot.Activities.IKaiActivity
import com.kai.gwtwohot.Adapters.BaseViewHolder
import com.kai.gwtwohot.Adapters.News.NewsInfo
import com.kai.gwtwohot.Extensions.getSmiley
import com.kai.gwtwohot.Fragments.WebFragment
import com.kai.gwtwohot.R
import com.kai.gwtwohot.Utils.GuildWarsUtils
import com.like.LikeButton
import com.squareup.picasso.Picasso

/**
 * Created by ikraammoothianpillay1 on 3/13/16.
 */

class ItemViewHolder(itemView: View,kaiActivity: IKaiActivity) : BaseViewHolder<ItemInfo>(itemView,kaiActivity) {

    protected var vName: TextView
    protected var vBuyPrice: TextView
    protected var vSellPrice: TextView
    protected var vBtnLike: LikeButton
    protected var vImgItem: ImageView
    protected var vItemLayout: RelativeLayout
    private var id: String? = null

    init {
        vName = itemView.findViewById(R.id.item_name) as TextView
        vBuyPrice = itemView.findViewById(R.id.item_buyprice) as TextView
        vSellPrice = itemView.findViewById(R.id.item_sellprice) as TextView
        vImgItem = itemView.findViewById(R.id.imgItem) as ImageView
        vBtnLike = itemView.findViewById(R.id.btnLike) as LikeButton
        vItemLayout = itemView.findViewById(R.id.itemLayout) as RelativeLayout
        vItemLayout.setOnClickListener(this)
    }

    override fun bind(item: ItemInfo) {
        this.vName.text = item.name
        this.vBuyPrice.text = kaiActivity.getContext().getString(R.string.buy_price).plus(" ").plus(if (item.buyprice.isNullOrEmpty()) "-" else GuildWarsUtils.getFormattedPrice(item.buyprice!!))
        this.vSellPrice.text = kaiActivity.getContext().getString(R.string.sell_price).plus(" ").plus(if (item.sellprice.isNullOrEmpty()) "-" else GuildWarsUtils.getFormattedPrice(item.sellprice!!))

        this.vBuyPrice.getSmiley()
        this.vSellPrice.getSmiley()

        this.id = item.id

        Picasso.with(kaiActivity.getContext()).load(item.iconUrl).into(this.vImgItem)
    }

    override fun onClick(view: View) {

    }
}
