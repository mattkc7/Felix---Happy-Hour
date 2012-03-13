package com.teamsexy.helloTabs;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.database.Cursor;
import android.content.ContentValues;

public class GroupHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "groupslist.db";
	private static final int SCHEMA_VERSION = 1;
	
	
	public GroupHelper(Context context) {
		super(context, DATABASE_NAME, null, SCHEMA_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE groups (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
	
	public Cursor getById(String id) {
	    String[] args={id};

	    return(getReadableDatabase()
	            .rawQuery("SELECT _id, name FROM groups WHERE _ID=?",
	                      args));
	  }
	
	public void insert(String name) {
		ContentValues cv=new ContentValues();
		cv.put("name", name); 
//		cv.put("address", address); 
//		cv.put("type", type); 
		getWritableDatabase().insert("groups", "name", cv); 
	}
	
	public void update(String id, String name) {
		ContentValues cv = new ContentValues();
		String[] args = { id };

		cv.put("name", name);
//		cv.put("address", address);
//		cv.put("type", type);

		getWritableDatabase().update("groups", cv, "_ID=?", args);
	}
	
	public Cursor getAll() { 
		return(getReadableDatabase().rawQuery("SELECT _id, name FROM groups ORDER BY name", null));
	}
	
	public String getName(Cursor c) { 
		return(c.getString(1));
	}
	
//	public String getAddress(Cursor c) {
//		return(c.getString(2)); 
//	}
//	
//	public String getType(Cursor c) { 
//		return(c.getString(3));
//	}
	
	
}
