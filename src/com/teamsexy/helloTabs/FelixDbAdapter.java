package com.teamsexy.helloTabs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * FelixDbAdapter
 * @author genia
 * 
 * Database adapter for Felix, the happy hour app. Provides basic CRUD
 * functionality for spots, events, other user info.
 *
 * Inspired by Android Notepad Tutorial, whose source can be found here:
 * [Placeholder]
 */
public class FelixDbAdapter {
	
	private static final String TAG = "FelixDB";
	private DatabaseHelper dbHelper;
    private SQLiteDatabase db;
    
    /**
     * Database creation sql statement
     */
    private static final String DATABASE_CREATE =
    		"create table spots (_id integer primary key autoincrement, "
    				+ "spot_name text not null, spot_about text not null, "
    				+ "latitude integer, longitude integer);";

    private static final String DATABASE_NAME = "felixdb";
    private static final String FDB_SPOTS_TABLE = "spots";
    private static final String FDB_EVENTS_TABLE = "events";
    private static final String FDB_GROUPS_TABLE = "groups";
    private static final int DATABASE_VERSION = 2;
    private final Context context;
    
    private static class DatabaseHelper extends SQLiteOpenHelper {

    	public DatabaseHelper(Context context) {
    		super(context, DATABASE_NAME, null, DATABASE_VERSION);
    	}
		
		@Override
		public void onCreate(SQLiteDatabase dbh) {
			dbh.execSQL(DATABASE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase dbh, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            dbh.execSQL("DROP TABLE IF EXISTS notes");
            onCreate(dbh);
		}
    }
    
    /**
     * Constructor - takes context to allow database management.
     */
    public FelixDbAdapter (Context context) {
    	this.context = context;
    }
    
    /** 
     * Open Felix database, either returning a FelixDbAdapter or throwing
     * a SQL exception if it couldn't be opened. 
     */
    public FelixDbAdapter open() throws SQLException {
    	dbHelper = new DatabaseHelper(context);
    	db = dbHelper.getWritableDatabase();
    	return this;
    }
    
    public void close() {
        dbHelper.close();
    }
    
    /*** CRUD Functionality: Events ***/
    //public Cursor getAllEventEntries() {}
    
    /*** CRUD Functionality: Groups ***/
	//public Cursor getAllGroupEntries() {}
    
    /*** CRUD Functionality: Spots ***/
    public Cursor getAllSpotEntries() {
    	return db.query(FDB_SPOTS_TABLE, 
    			new String[] {"_id", "spot_name", "spot_about", "latitude", "longitude"},
               null, null, null, null, null);
    }
    
}
