package com.teamsexy.helloTabs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * FelixDbAdapter
 * @author genia
 * 
 * Database adapter for Felix, the happy hour app. Provides basic CRUD
 * functionality for spots, events, other user info.
 *
 * Inspired by Android Notepad Tutorial.
 */
public class FelixDbAdapter {
	
	private DatabaseHelper dbHelper;
    private SQLiteDatabase db;
    
    /**
     * Database creation sql statement
     */
    //private static final String DATABASE_CREATE =
    //  "create table notes (_id integer primary key autoincrement, "
    //    + "title text not null, body text not null);";

    private static final String DATABASE_NAME = "felixdb";
    private static final int DATABASE_VERSION = 2;
    private final Context context;
    
    private static class DatabaseHelper extends SQLiteOpenHelper {

    	public DatabaseHelper(Context context) {
    		super(context, DATABASE_NAME, null, DATABASE_VERSION);
    	}
		
		@Override
		public void onCreate(SQLiteDatabase arg0) {
		}

		@Override
		public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		}
    }
    
    /**
     * Constructor - takes context to allow database management.
     */
    public FelixDbAdapter (Context context) {
    	this.context = context;
    }
    
}
