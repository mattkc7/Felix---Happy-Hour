package com.teamsexy.helloTabs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GroupMemberHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "groupmembers.db";
	private static final int SCHEMA_VERSION = 1;
	
	public GroupMemberHelper(Context context) {
		super(context, DATABASE_NAME, null, SCHEMA_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE contacts_to_groups (_id INTEGER PRIMARY KEY AUTOINCREMENT, group_id INTEGER, name TEXT, number TEXT);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
	
	public void addContactToGroup(Integer group, String name, String number) {
		// Check if already exists before adding
		String[] args = { group.toString(), name };
		Cursor c = getReadableDatabase().rawQuery("SELECT _id FROM contacts_to_groups WHERE group_id=? AND name=?", 
				args);
		
		// Add member to group
		if (c.getCount() == 0) {
			ContentValues cv= new ContentValues();
			cv.put("group_id", group);
			cv.put("name", name);
			cv.put("number", number);
			getWritableDatabase().insert("contacts_to_groups", null, cv);
		}
		
		c.close();
	}
	
	public Cursor getContactsByGroupId(String id) {
		// Get contacts from contacts_to_groups table
		String[] args={id};
		
		return(getReadableDatabase()
	            .rawQuery("SELECT _id, name FROM contacts_to_groups WHERE group_id=?",
	                      args));
		
	}

}
