package com.kai.gwtwohot.DaoRepositories

import android.content.Context
import com.kai.gwtwohot.Dungeon
import com.kai.gwtwohot.DungeonDao
import com.kai.gwtwohot.GuildWarsApplication

/**
 * Created by ikraammoothianpillay1 on 2/20/16.
 */

object DungeonRepository {

    fun insertOrUpdate(context: Context, dungeon: Dungeon) {
        getDungeonDao(context).insertOrReplace(dungeon)
    }

    fun clearDungeon(context: Context) {
        getDungeonDao(context).deleteAll()
    }

    fun deleteDungeonById(context: Context, id: Long) {
        getDungeonDao(context).delete(getDungeonById(context, id))
    }

    fun deleteDungeon(context: Context, dungeon: Dungeon) {
        getDungeonDao(context).delete(dungeon)
    }

    fun getDungeonById(context: Context, id: Long): Dungeon {
        return getDungeonDao(context).load(id)
    }

    fun getDungeonByIdSelected(context: Context, id: String): Dungeon? {
        val qb = getDungeonDao(context).queryBuilder()
        qb.where(DungeonDao.Properties.IdSelected.eq(id))
        val dungeons = qb.list()
        if (dungeons.size >= 1) {
            return dungeons[0]
        }
        return null
    }

    fun getAllDungeonSelected(context: Context): List<Dungeon> {
        return getDungeonDao(context).loadAll()
    }

    private fun getDungeonDao(c: Context): DungeonDao {
        return (c.applicationContext as GuildWarsApplication).daoSession!!.dungeonDao
    }
}
