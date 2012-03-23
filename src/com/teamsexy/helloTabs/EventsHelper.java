package com.teamsexy.helloTabs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EventsHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "eventslist.db";
	private static final int SCHEMA_VERSION = 1;
	
	public EventsHelper(Context context) {
		super(context, DATABASE_NAME, null, SCHEMA_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE events (_id INTEGER PRIMARY KEY AUTOINCREMENT, chosenSpot TEXT, chosenTime TEXT, " +
				"chosenDate TEXT, chosenGroup TEXT);");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
	
	public Cursor getById(String id) {
	    String[] args={id};

	    // Fill Me In
	    return(getReadableDatabase()
	            .rawQuery("SELECT _id, chosenSpot FROM events WHERE _ID=?", args));
	}
	
	public void insert(String spot, String time, String date, String group) {
		ContentValues cv=new ContentValues();
		cv.put("chosenSpot", spot); 
		cv.put("chosenTime", time); 
		cv.put("chosenDate", date); 
		cv.put("chosenGroup", group); 
		getWritableDatabase().insert("events", "chosenSpot", cv); 
	}
	
	public int delete(String id) {
		return getWritableDatabase().delete("events", "_id=" + id, null);
	}
	
	public void update(String id, String spot, String time, String date, String group) {
		ContentValues cv = new ContentValues();
		String[] args = { id };

		cv.put("chosenSpot", spot); 
		cv.put("chosenTime", time); 
		cv.put("chosenDate", date); 
		cv.put("chosenGroup", group); 

		getWritableDatabase().update("events", cv, "_id=?", args);
	}
	
	public Cursor getAll() { 
		return(getReadableDatabase().rawQuery("SELECT * FROM events ORDER BY chosenSpot", null));
	}
	
	public String getEventId(Cursor c) {
		return(c.getString(0));
	}
	
	public String getSpot(Cursor c) { 
		//return(c.getString(1));
		return(c.getString(1));
	}
	
	public String getTime(Cursor c) { 
		return(c.getString(2));
		//return String.valueOf(c.getColumnCount());
	}
	
	public String getGroup(Cursor c) { 
		return(c.getString(4));
		//return String.valueOf(c.getColumnCount());
	}
	
	public String getDate(Cursor c) { 
		return(c.getString(3));
		//return String.valueOf(c.getColumnCount());
	}
	
}
