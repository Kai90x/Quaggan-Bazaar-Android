package com.kai.gwtwohot.DaoRepositories

import android.content.Context
import com.kai.gwtwohot.Dungeon
import com.kai.gwtwohot.DungeonDao
import com.kai.gwtwohot.Extensions.format
import com.kai.gwtwohot.GuildWarsApplication
import java.util.*

/**
 * Created by ikraammoothianpillay1 on 2/20/16.
 */

object DungeonRepository {

    fun insertOrUpdate(context: Context, dungeon: Dungeon) {
        getDungeonDao(context).insertOrReplace(dungeon)
    }

    fun clear(context: Context) = getDungeonDao(context).deleteAll()

    fun delete(context: Context, id: Long) = getDungeonDao(context).delete(get(context, id))

    fun delete(context: Context, dungeon: Dungeon) = getDungeonDao(context).delete(dungeon)

    fun get(context: Context, id: Long): Dungeon = getDungeonDao(context).load(id)

    fun get(context: Context, selected: Boolean): Dungeon? {
        val qb = getDungeonDao(context).queryBuilder()
        qb.where(DungeonDao.Properties.IsSelected.eq(selected))
        val dungeons = qb.list()
        if (dungeons.size >= 1) {
            return dungeons[0]
        }
        return null
    }

    fun get(context: Context, date: Date = Date()): List<Dungeon> {
        val morning = GregorianCalendar.getInstance()
        morning.time = date
        val night = GregorianCalendar.getInstance()
        night.time = date

        //Set Time to 00:00:00
        morning.set(Calendar.AM_PM, Calendar.AM);
        morning.set(Calendar.HOUR, 0);
        morning.set(Calendar.MINUTE, 0);
        morning.set(Calendar.SECOND, 0);
        morning.set(Calendar.MILLISECOND, 0);

        //Set Time to 23:59:59
        night.set(Calendar.AM_PM, Calendar.PM);
        night.set(Calendar.HOUR, 23);
        night.set(Calendar.MINUTE, 59);
        night.set(Calendar.SECOND, 59);
        night.set(Calendar.MILLISECOND, 99);

        val qb = getDungeonDao(context).queryBuilder()
        qb.where(qb.and(DungeonDao.Properties.DateAdded.ge(morning.time),DungeonDao.Properties.DateAdded.le(night.time)))
        return qb.list()
    }

    fun hasAny(context: Context, date: Date = Date()): Boolean {
        val morning = GregorianCalendar.getInstance()
        morning.time = date
        val night = GregorianCalendar.getInstance()
        night.time = date

        //Set Time to 00:00:00
        morning.set(Calendar.AM_PM, Calendar.AM);
        morning.set(Calendar.HOUR, 0);
        morning.set(Calendar.MINUTE, 0);
        morning.set(Calendar.SECOND, 0);
        morning.set(Calendar.MILLISECOND, 0);

        //Set Time to 23:59:59
        night.set(Calendar.AM_PM, Calendar.PM);
        night.set(Calendar.HOUR, 23);
        night.set(Calendar.MINUTE, 59);
        night.set(Calendar.SECOND, 59);
        night.set(Calendar.MILLISECOND, 99);

        val qb = getDungeonDao(context).queryBuilder()
        qb.where(qb.and(DungeonDao.Properties.DateAdded.ge(morning.time),DungeonDao.Properties.DateAdded.le(night.time)))
        return qb.count() > 0
    }

    fun get(context: Context): List<Dungeon> = getDungeonDao(context).loadAll()

    private fun getDungeonDao(c: Context): DungeonDao {
        return (c.applicationContext as GuildWarsApplication).daoSession!!.dungeonDao
    }
}
