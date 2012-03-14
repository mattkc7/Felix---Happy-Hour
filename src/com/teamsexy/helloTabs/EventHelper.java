package com.teamsexy.helloTabs;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class EventHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "eventslist.db";
	private static final int SCHEMA_VERSION = 1;
	
	public EventHelper(Context context) {
		super(context, DATABASE_NAME, null, SCHEMA_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
	
	public Cursor getById(String id) {
	    String[] args={id};

	    // Fill Me In
	    return null;
	}
	
	public void insert(String eventName, String eventAbout) {
		// Also need time/place/spot, etc
	}
	
	public void update() {
		
	}
	
	public Cursor getAll() {
		return null;
	}
}
