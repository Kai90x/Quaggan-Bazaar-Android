package com.kai.gwtwohot;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.kai.gwtwohot.Dungeon;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "DUNGEON".
*/
public class DungeonDao extends AbstractDao<Dungeon, Long> {

    public static final String TABLENAME = "DUNGEON";

    /**
     * Properties of entity Dungeon.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Dungeon = new Property(1, String.class, "dungeon", false, "DUNGEON");
        public final static Property Path = new Property(2, String.class, "path", false, "PATH");
        public final static Property Goldreward = new Property(3, String.class, "goldreward", false, "GOLDREWARD");
        public final static Property Tokenreward = new Property(4, String.class, "tokenreward", false, "TOKENREWARD");
        public final static Property IsSelected = new Property(5, boolean.class, "isSelected", false, "IS_SELECTED");
        public final static Property DateAdded = new Property(6, java.util.Date.class, "dateAdded", false, "DATE_ADDED");
    };


    public DungeonDao(DaoConfig config) {
        super(config);
    }
    
    public DungeonDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"DUNGEON\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"DUNGEON\" TEXT NOT NULL ," + // 1: dungeon
                "\"PATH\" TEXT NOT NULL ," + // 2: path
                "\"GOLDREWARD\" TEXT NOT NULL ," + // 3: goldreward
                "\"TOKENREWARD\" TEXT NOT NULL ," + // 4: tokenreward
                "\"IS_SELECTED\" INTEGER NOT NULL ," + // 5: isSelected
                "\"DATE_ADDED\" INTEGER NOT NULL );"); // 6: dateAdded
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"DUNGEON\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Dungeon entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getDungeon());
        stmt.bindString(3, entity.getPath());
        stmt.bindString(4, entity.getGoldreward());
        stmt.bindString(5, entity.getTokenreward());
        stmt.bindLong(6, entity.getIsSelected() ? 1L: 0L);
        stmt.bindLong(7, entity.getDateAdded().getTime());
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Dungeon readEntity(Cursor cursor, int offset) {
        Dungeon entity = new Dungeon( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // dungeon
            cursor.getString(offset + 2), // path
            cursor.getString(offset + 3), // goldreward
            cursor.getString(offset + 4), // tokenreward
            cursor.getShort(offset + 5) != 0, // isSelected
            new java.util.Date(cursor.getLong(offset + 6)) // dateAdded
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Dungeon entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setDungeon(cursor.getString(offset + 1));
        entity.setPath(cursor.getString(offset + 2));
        entity.setGoldreward(cursor.getString(offset + 3));
        entity.setTokenreward(cursor.getString(offset + 4));
        entity.setIsSelected(cursor.getShort(offset + 5) != 0);
        entity.setDateAdded(new java.util.Date(cursor.getLong(offset + 6)));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Dungeon entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Dungeon entity) {
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
