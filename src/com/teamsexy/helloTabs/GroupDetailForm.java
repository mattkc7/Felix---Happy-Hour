package com.teamsexy.helloTabs;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class GroupDetailForm extends Activity { 

	String spotId = null;
	EditText name = null;
	ArrayList<ContactEntry> contactsToAdd;

	GroupHelper helper=null;
	GroupMemberHelper memberHelper;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.group_detail_form);

		helper = new GroupHelper(this);
		memberHelper = new GroupMemberHelper(this);

		name=(EditText)findViewById(R.id.name); 

		contactsToAdd = new ArrayList<ContactEntry>();
		
		Button save = (Button)findViewById(R.id.save);
		save.setOnClickListener(onSave);
		Button addFriends = (Button)findViewById(R.id.addFriends);
		addFriends.setOnClickListener(onAddFriends);

		spotId = getIntent().getStringExtra(GroupsActivity.ID_EXTRA);

		if (spotId != null){
			load();
		}
	}

	public void onDestroy(){
		super.onDestroy();
		helper.close(); 
	}

	private void load() {
		Cursor c=helper.getById(spotId);

		c.moveToFirst();    
		name.setText(helper.getName(c));

		c.close();
	}

	private View.OnClickListener onSave = new View.OnClickListener() {
		public void onClick(View v) {
			
			if (spotId == null){
				Long groupId = helper.insert(name.getText().toString()); //, address.getText().toString(), type);
				
				if (groupId.intValue() != -1) {
					for (ContactEntry contact : contactsToAdd) {
						memberHelper.addContactToGroup(groupId.intValue(), contact.getName(), contact.getNumber());
					}
				}
				
			} else {
				helper.update(spotId, name.getText().toString()); //, address.getText().toString(), type);
			
				for (ContactEntry contact : contactsToAdd) {
					memberHelper.addContactToGroup(Integer.getInteger(spotId), contact.getName(), contact.getNumber());
				}
				
			}

			finish();
		}
	};

	private View.OnClickListener onAddFriends = new View.OnClickListener() {
		public void onClick(View v) {

			startActivityForResult(new Intent(Intent.ACTION_PICK, 
					ContactsContract.Contacts.CONTENT_URI), 1);
		}
	};

	class ContactEntry {
		String name;
		String number;
		
		public ContactEntry (String name, String num) {
			this.name = name;
			this.number = num;
		}
		
		public String getName () {
			return this.name;
		}
		
		public String getNumber () {
			return this.number;
			
		}
	}
	
	@Override
	public void onActivityResult(int reqCode, int resultCode, Intent data) {
		super.onActivityResult(reqCode, resultCode, data);

		switch (reqCode) {
		case (1) :
			if (resultCode == Activity.RESULT_OK) {

				Uri contactData = data.getData();
				Cursor c =  managedQuery(contactData, null, null, null, null);
				if (c.moveToFirst()) {
					
					// Get contact name and ID
					String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
					String contactId =
				            c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));
					String number = "";
					
					// Get contact phone, if one exists
					ContentResolver cr = getContentResolver();
					Cursor phones = cr.query(Phone.CONTENT_URI, null,
				            Phone.CONTACT_ID + " = " + contactId, null, null);
					if (phones.moveToFirst()) {
						number = phones.getString(phones.getColumnIndex(Phone.NUMBER));
					}
					
					phones.close();
					
					// Add contact to current group list
					contactsToAdd.add(new ContactEntry(name, number));
				}
			}
		break;
		}
	}
}