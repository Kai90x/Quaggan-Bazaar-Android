package com.kai.gwtwohot.DaoRepositories

import android.content.Context
import com.kai.gwtwohot.GuildWarsApplication
import com.kai.gwtwohot.NotificationItem
import com.kai.gwtwohot.NotificationItemDao

/**
 * Created by ikraammoothianpillay1 on 2/20/16.
 */

object NotificationItemRepository {
    fun insertOrUpdate(context: Context, item: NotificationItem) {
        getNotificationItemDao(context).insertOrReplace(item)
    }

    fun clearNotificationItems(context: Context) {
        getNotificationItemDao(context).deleteAll()
    }

    fun deleteNotificationItemWithId(context: Context, id: Long) {
        getNotificationItemDao(context).delete(getNotificationItemById(context, id))
    }

    fun deleteNotificationByGWId(context: Context, id: Int) {
        val item = getNotificationByGWId(context, id)
        if (item != null)
            getNotificationItemDao(context).delete(item)
    }

    fun getNotificationByGWId(context: Context, id: Int): NotificationItem? {
        val qb = getNotificationItemDao(context).queryBuilder()
        qb.where(NotificationItemDao.Properties.Gw_item_id.eq(id))
        val item = qb.list()
        if (item.size >= 1)
            return item[0]

        return null
    }

    fun getNotificationNotNotified(context: Context): List<NotificationItem>? {
        val qb = getNotificationItemDao(context).queryBuilder()
        qb.where(NotificationItemDao.Properties.IsNotified.eq(false))
        val items = qb.list()
        if (items.size > 0)
            return items

        return null
    }

    fun getNotificationItemById(context: Context, id: Long): NotificationItem {
        return getNotificationItemDao(context).load(id)
    }

    fun getAllNotificationItems(context: Context): List<NotificationItem> {
        return getNotificationItemDao(context).loadAll()
    }

    private fun getNotificationItemDao(c: Context): NotificationItemDao {
        return (c.applicationContext as GuildWarsApplication).daoSession!!.notificationItemDao
    }
}