package com.teamsexy.helloTabs;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.database.Cursor;
import android.content.ContentValues;

public class SpotHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "spotslist.db";
	private static final int SCHEMA_VERSION = 1;
	
	
	public SpotHelper(Context context) {
		super(context, DATABASE_NAME, null, SCHEMA_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE spots (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, address TEXT, type TEXT);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
	
	public Cursor getById(String id) {
	    String[] args={id};

	    return(getReadableDatabase()
	            .rawQuery("SELECT _id, name, address, type FROM spots WHERE _ID=?",
	                      args));
	  }
	
	public void insert(String name, String address, String type) {
		ContentValues cv=new ContentValues();
		cv.put("name", name); 
		cv.put("address", address); 
		cv.put("type", type); 
		getWritableDatabase().insert("spots", "name", cv); 
	}
	
	public void update(String id, String name, String address, String type) {
		ContentValues cv = new ContentValues();
		String[] args = { id };

		cv.put("name", name);
		cv.put("address", address);
		cv.put("type", type);

		getWritableDatabase().update("spots", cv, "_ID=?", args);
	}
	
	public Cursor getAll() { 
		return(getReadableDatabase().rawQuery("SELECT _id, name, address, type FROM spots ORDER BY name", null));
	}
	
	public String getName(Cursor c) { 
		return(c.getString(1));
	}
	
	public String getAddress(Cursor c) {
		return(c.getString(2)); 
	}
	
	public String getType(Cursor c) { 
		return(c.getString(3));
	}
	
	
}
