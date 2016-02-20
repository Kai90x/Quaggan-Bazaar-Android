package com.kai.gwtwohot;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.kai.gwtwohot.FavoriteItem;
import com.kai.gwtwohot.APIKey;
import com.kai.gwtwohot.Event;
import com.kai.gwtwohot.NotificationItem;
import com.kai.gwtwohot.Dungeon;

import com.kai.gwtwohot.FavoriteItemDao;
import com.kai.gwtwohot.APIKeyDao;
import com.kai.gwtwohot.EventDao;
import com.kai.gwtwohot.NotificationItemDao;
import com.kai.gwtwohot.DungeonDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig favoriteItemDaoConfig;
    private final DaoConfig aPIKeyDaoConfig;
    private final DaoConfig eventDaoConfig;
    private final DaoConfig notificationItemDaoConfig;
    private final DaoConfig dungeonDaoConfig;

    private final FavoriteItemDao favoriteItemDao;
    private final APIKeyDao aPIKeyDao;
    private final EventDao eventDao;
    private final NotificationItemDao notificationItemDao;
    private final DungeonDao dungeonDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        favoriteItemDaoConfig = daoConfigMap.get(FavoriteItemDao.class).clone();
        favoriteItemDaoConfig.initIdentityScope(type);

        aPIKeyDaoConfig = daoConfigMap.get(APIKeyDao.class).clone();
        aPIKeyDaoConfig.initIdentityScope(type);

        eventDaoConfig = daoConfigMap.get(EventDao.class).clone();
        eventDaoConfig.initIdentityScope(type);

        notificationItemDaoConfig = daoConfigMap.get(NotificationItemDao.class).clone();
        notificationItemDaoConfig.initIdentityScope(type);

        dungeonDaoConfig = daoConfigMap.get(DungeonDao.class).clone();
        dungeonDaoConfig.initIdentityScope(type);

        favoriteItemDao = new FavoriteItemDao(favoriteItemDaoConfig, this);
        aPIKeyDao = new APIKeyDao(aPIKeyDaoConfig, this);
        eventDao = new EventDao(eventDaoConfig, this);
        notificationItemDao = new NotificationItemDao(notificationItemDaoConfig, this);
        dungeonDao = new DungeonDao(dungeonDaoConfig, this);

        registerDao(FavoriteItem.class, favoriteItemDao);
        registerDao(APIKey.class, aPIKeyDao);
        registerDao(Event.class, eventDao);
        registerDao(NotificationItem.class, notificationItemDao);
        registerDao(Dungeon.class, dungeonDao);
    }
    
    public void clear() {
        favoriteItemDaoConfig.getIdentityScope().clear();
        aPIKeyDaoConfig.getIdentityScope().clear();
        eventDaoConfig.getIdentityScope().clear();
        notificationItemDaoConfig.getIdentityScope().clear();
        dungeonDaoConfig.getIdentityScope().clear();
    }

    public FavoriteItemDao getFavoriteItemDao() {
        return favoriteItemDao;
    }

    public APIKeyDao getAPIKeyDao() {
        return aPIKeyDao;
    }

    public EventDao getEventDao() {
        return eventDao;
    }

    public NotificationItemDao getNotificationItemDao() {
        return notificationItemDao;
    }

    public DungeonDao getDungeonDao() {
        return dungeonDao;
    }

}
