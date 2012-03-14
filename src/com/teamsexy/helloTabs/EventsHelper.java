package com.teamsexy.helloTabs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class EventsHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "eventslist.db";
	private static final int SCHEMA_VERSION = 1;
	
	public EventsHelper(Context context) {
		super(context, DATABASE_NAME, null, SCHEMA_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE events (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT);");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
	
	public Cursor getById(String id) {
	    String[] args={id};

	    // Fill Me In
	    return(getReadableDatabase()
	            .rawQuery("SELECT _id, name FROM events WHERE _ID=?", args));
	}
	
	public void insert(String name) {
		ContentValues cv=new ContentValues();
		cv.put("name", name); 
//		cv.put("address", address); 
//		cv.put("type", type); 
		getWritableDatabase().insert("events", "name", cv); 
	}
	
	public void update(String id, String name) {
		ContentValues cv = new ContentValues();
		String[] args = { id };

		cv.put("name", name);
//		cv.put("address", address);
//		cv.put("type", type);

		getWritableDatabase().update("events", cv, "_ID=?", args);
	}
	
	public Cursor getAll() { 
		return(getReadableDatabase().rawQuery("SELECT _id, name FROM events ORDER BY name", null));
	}
	
	public String getName(Cursor c) { 
		return(c.getString(1));
	}
	
}
