package com.kai.gwtwohot.DaoRepositories

import android.content.Context
import com.kai.gwtwohot.APIKey
import com.kai.gwtwohot.APIKeyDao
import com.kai.gwtwohot.GuildWarsApplication

/**
 * Created by ikraammoothianpillay1 on 2/20/16.
 */

object APIKeyRepository {

    fun insertOrUpdate(context: Context, key: APIKey) {
        if (key.isSelected) {
            //Set other API key's unselected
            if (key.id != null) {
                val apiKeys = getAPIKeyWhereAPIisNot(context, key.id!!)

                if (apiKeys != null) {
                    for (apiKey in apiKeys) {
                        apiKey.isSelected = false
                        getAPIKeyDao(context).insertOrReplace(apiKey)
                    }
                }
            }
        }

        getAPIKeyDao(context).insertOrReplace(key)
    }

    fun clearAPIKeys(context: Context) {
        getAPIKeyDao(context).deleteAll()
    }

    fun deleteAPIKeyById(context: Context, id: Long) {
        getAPIKeyDao(context).delete(getAPIKeyById(context, id))
    }

    fun getAPIKeyById(context: Context, id: Long): APIKey {
        return getAPIKeyDao(context).load(id)
    }

    fun getAPIKeyByIsSelected(context: Context): APIKey? {
        val qb = getAPIKeyDao(context).queryBuilder()
        qb.where(APIKeyDao.Properties.IsSelected.eq(true))
        val keys = qb.list()
        if (keys.size >= 1)
            return keys[0]

        return null
    }

    fun getAPIKeymByKey(context: Context, key: String): APIKey? {
        val qb = getAPIKeyDao(context).queryBuilder()
        qb.where(APIKeyDao.Properties.Key.eq(key))
        val keys = qb.list()
        if (keys.size >= 1)
            return keys[0]

        return null
    }

    fun getAPIKeyWhereAPIisNot(context: Context, id: Long): List<APIKey>? {
        val qb = getAPIKeyDao(context).queryBuilder()
        qb.where(APIKeyDao.Properties.Id.notEq(id))
        val keys = qb.list()
        if (keys.size >= 1)
            return keys

        return null
    }

    fun getAllAPIKeys(context: Context): List<APIKey> {
        return getAPIKeyDao(context).loadAll()
    }

    private fun getAPIKeyDao(c: Context): APIKeyDao {
        return (c.applicationContext as GuildWarsApplication).daoSession!!.apiKeyDao
    }
}
