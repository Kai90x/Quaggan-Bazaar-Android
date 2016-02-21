package com.kai.gwtwohot;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.kai.gwtwohot.NotificationItem;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "NOTIFICATION_ITEM".
*/
public class NotificationItemDao extends AbstractDao<NotificationItem, Long> {

    public static final String TABLENAME = "NOTIFICATION_ITEM";

    /**
     * Properties of entity NotificationItem.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Gw_item_id = new Property(1, int.class, "gw_item_id", false, "GW_ITEM_ID");
        public final static Property Name = new Property(2, String.class, "name", false, "NAME");
        public final static Property Icon = new Property(3, String.class, "icon", false, "ICON");
        public final static Property BuyPrice = new Property(4, Integer.class, "buyPrice", false, "BUY_PRICE");
        public final static Property SellPrice = new Property(5, Integer.class, "sellPrice", false, "SELL_PRICE");
        public final static Property IsGreater = new Property(6, Boolean.class, "isGreater", false, "IS_GREATER");
        public final static Property IsBuy = new Property(7, Boolean.class, "isBuy", false, "IS_BUY");
        public final static Property IsNotified = new Property(8, boolean.class, "isNotified", false, "IS_NOTIFIED");
    };


    public NotificationItemDao(DaoConfig config) {
        super(config);
    }
    
    public NotificationItemDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"NOTIFICATION_ITEM\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"GW_ITEM_ID\" INTEGER NOT NULL ," + // 1: gw_item_id
                "\"NAME\" TEXT NOT NULL ," + // 2: name
                "\"ICON\" TEXT NOT NULL ," + // 3: icon
                "\"BUY_PRICE\" INTEGER," + // 4: buyPrice
                "\"SELL_PRICE\" INTEGER," + // 5: sellPrice
                "\"IS_GREATER\" INTEGER," + // 6: isGreater
                "\"IS_BUY\" INTEGER," + // 7: isBuy
                "\"IS_NOTIFIED\" INTEGER NOT NULL );"); // 8: isNotified
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"NOTIFICATION_ITEM\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, NotificationItem entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getGw_item_id());
        stmt.bindString(3, entity.getName());
        stmt.bindString(4, entity.getIcon());
 
        Integer buyPrice = entity.getBuyPrice();
        if (buyPrice != null) {
            stmt.bindLong(5, buyPrice);
        }
 
        Integer sellPrice = entity.getSellPrice();
        if (sellPrice != null) {
            stmt.bindLong(6, sellPrice);
        }
 
        Boolean isGreater = entity.getIsGreater();
        if (isGreater != null) {
            stmt.bindLong(7, isGreater ? 1L: 0L);
        }
 
        Boolean isBuy = entity.getIsBuy();
        if (isBuy != null) {
            stmt.bindLong(8, isBuy ? 1L: 0L);
        }
        stmt.bindLong(9, entity.getIsNotified() ? 1L: 0L);
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public NotificationItem readEntity(Cursor cursor, int offset) {
        NotificationItem entity = new NotificationItem( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getInt(offset + 1), // gw_item_id
            cursor.getString(offset + 2), // name
            cursor.getString(offset + 3), // icon
            cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4), // buyPrice
            cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5), // sellPrice
            cursor.isNull(offset + 6) ? null : cursor.getShort(offset + 6) != 0, // isGreater
            cursor.isNull(offset + 7) ? null : cursor.getShort(offset + 7) != 0, // isBuy
            cursor.getShort(offset + 8) != 0 // isNotified
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, NotificationItem entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setGw_item_id(cursor.getInt(offset + 1));
        entity.setName(cursor.getString(offset + 2));
        entity.setIcon(cursor.getString(offset + 3));
        entity.setBuyPrice(cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4));
        entity.setSellPrice(cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5));
        entity.setIsGreater(cursor.isNull(offset + 6) ? null : cursor.getShort(offset + 6) != 0);
        entity.setIsBuy(cursor.isNull(offset + 7) ? null : cursor.getShort(offset + 7) != 0);
        entity.setIsNotified(cursor.getShort(offset + 8) != 0);
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(NotificationItem entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(NotificationItem entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}