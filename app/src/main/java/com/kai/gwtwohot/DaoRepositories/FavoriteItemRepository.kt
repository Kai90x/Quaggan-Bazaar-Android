package com.kai.gwtwohot.DaoRepositories

import android.content.Context
import com.kai.gwtwohot.FavoriteItem
import com.kai.gwtwohot.FavoriteItemDao
import com.kai.gwtwohot.GuildWarsApplication

/**
 * Created by ikraammoothianpillay1 on 2/20/16.
 */

object FavoriteItemRepository {

    fun insertOrUpdate(context: Context, item: FavoriteItem) {
        getFavoriteItemDao(context).insertOrReplace(item)
    }

    fun clearFavoriteItems(context: Context) {
        getFavoriteItemDao(context).deleteAll()
    }

    fun deleteFavoriteItemWithId(context: Context, id: Long) {
        getFavoriteItemDao(context).delete(getFavoriteItemById(context, id))
    }

    fun deleteFavoriteItemWithGWItemId(context: Context, id: Int) {
        val item = getFavoriteItemByGWItemId(context, id)
        if (item != null)
            getFavoriteItemDao(context).delete(item)
    }

    fun getFavoriteItemById(context: Context, id: Long): FavoriteItem {
        return getFavoriteItemDao(context).load(id)
    }

    fun getFavoriteItemByGWItemId(context: Context, id: Int): FavoriteItem? {
        val qb = getFavoriteItemDao(context).queryBuilder()
        qb.where(FavoriteItemDao.Properties.Gw_item_id.eq(id))
        val item = qb.list()
        if (item.size >= 1)
            return item[0]

        return null
    }

    fun getAllFavoriteItems(context: Context): List<FavoriteItem> {
        return getFavoriteItemDao(context).loadAll()
    }

    private fun getFavoriteItemDao(c: Context): FavoriteItemDao {
        return (c.applicationContext as GuildWarsApplication).daoSession!!.favoriteItemDao
    }
}
