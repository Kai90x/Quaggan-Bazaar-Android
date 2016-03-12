package com.kai.gwtwohot.Adapters.Events

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.kai.gwtwohot.Activities.IKaiActivity
import com.kai.gwtwohot.Adapters.BaseViewHolder
import com.kai.gwtwohot.R
import com.makeramen.roundedimageview.RoundedImageView
import com.squareup.picasso.Picasso

/**
 * Created by ikraammoothianpillay1 on 3/7/16.
 */
class EventViewHolder(itemView: View,kaiActivity: IKaiActivity) : BaseViewHolder<EventInfo>(itemView,kaiActivity) {
    protected var vImage: RoundedImageView
    protected var vBossName: TextView
    protected var vTime: TextView
    protected var vLocation: TextView
    protected var vActive: TextView

    init {
        vImage = itemView.findViewById(R.id.imgBoss) as RoundedImageView
        vBossName = itemView.findViewById(R.id.txtBossName) as TextView
        vTime = itemView.findViewById(R.id.txtTime) as TextView
        vActive = itemView.findViewById(R.id.txtActive) as TextView
        vLocation = itemView.findViewById(R.id.txtLocation) as TextView
    }

    override fun bind(item: EventInfo) {
        vBossName.text = item.name
        vTime.text = item.time
        vLocation.text = item.location

        if (item.isActive)
            vActive.visibility = View.VISIBLE;
        else
            vActive.visibility = View.GONE;

        Picasso.with(kaiActivity.getContext()).load(item.bossUrl).into(vImage)
    }

    override fun onClick(p0: View?) {
    }
}
