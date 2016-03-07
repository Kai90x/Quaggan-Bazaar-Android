package com.kai.gwtwohot.DaoRepositories

import android.content.Context
import com.kai.gwtwohot.Event
import com.kai.gwtwohot.EventDao
import com.kai.gwtwohot.GuildWarsApplication

/**
 * Created by ikraammoothianpillay1 on 2/20/16.
 */

object EventRepository {
    fun insertOrUpdate(context: Context, event: Event) {
        getEventDao(context).insertOrReplace(event)
    }

    fun clear(context: Context) {
        getEventDao(context).deleteAll()
    }

    fun deleteById(context: Context, id: Long) {
        getEventDao(context).delete(get(context, id))
    }


    fun get(context: Context, boss: String): List<Event>? {
        val qb = getEventDao(context).queryBuilder()
        qb.where(EventDao.Properties.Boss.eq(boss)).orderAsc(EventDao.Properties.Spawntime_utc_minutes)
        val events = qb.list()
        if (events.size > 0)
            return events

        return null
    }

    fun get(context: Context, start: Int, end: Int): List<Event>? {
        val qb = getEventDao(context).queryBuilder()
        qb.where(qb.and(EventDao.Properties.Spawntime_utc_minutes.ge(start), EventDao.Properties.Spawntime_utc_minutes.le(end)))
        val events = qb.list()
        if (events.size > 0)
            return events

        return null
    }

    fun get(context: Context, time: Int): List<Event>? {
        val qb = getEventDao(context).queryBuilder()
        qb.where(qb.or(EventDao.Properties.Spawntime_utc_minutes.ge(time), EventDao.Properties.Spawntime_utc_minutes.eq(time))).orderAsc(EventDao.Properties.Spawntime_utc_minutes)

        val events = qb.list()
        if (events.size > 0)
            return events

        return null
    }

    fun get(context: Context, id: Long): Event {
        return getEventDao(context).load(id)
    }

    fun get(context: Context): List<Event> {
        return getEventDao(context).loadAll()
    }

    fun hasAny(context: Context): Boolean {
        return getEventDao(context).count() > 0
    }

    private fun getEventDao(c: Context): EventDao {
        return (c.applicationContext as GuildWarsApplication).daoSession!!.eventDao
    }
}
