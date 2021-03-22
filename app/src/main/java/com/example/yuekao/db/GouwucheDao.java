package com.example.yuekao.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "GOUWUCHE".
*/
public class GouwucheDao extends AbstractDao<Gouwuche, Long> {

    public static final String TABLENAME = "GOUWUCHE";

    /**
     * Properties of entity Gouwuche.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Shuliang = new Property(1, String.class, "shuliang", false, "SHULIANG");
        public final static Property Pic = new Property(2, String.class, "pic", false, "PIC");
        public final static Property Title = new Property(3, String.class, "title", false, "TITLE");
    }


    public GouwucheDao(DaoConfig config) {
        super(config);
    }
    
    public GouwucheDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"GOUWUCHE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"SHULIANG\" TEXT," + // 1: shuliang
                "\"PIC\" TEXT," + // 2: pic
                "\"TITLE\" TEXT);"); // 3: title
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"GOUWUCHE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Gouwuche entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String shuliang = entity.getShuliang();
        if (shuliang != null) {
            stmt.bindString(2, shuliang);
        }
 
        String pic = entity.getPic();
        if (pic != null) {
            stmt.bindString(3, pic);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(4, title);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Gouwuche entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String shuliang = entity.getShuliang();
        if (shuliang != null) {
            stmt.bindString(2, shuliang);
        }
 
        String pic = entity.getPic();
        if (pic != null) {
            stmt.bindString(3, pic);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(4, title);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Gouwuche readEntity(Cursor cursor, int offset) {
        Gouwuche entity = new Gouwuche( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // shuliang
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // pic
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3) // title
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Gouwuche entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setShuliang(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setPic(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setTitle(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Gouwuche entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Gouwuche entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Gouwuche entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
