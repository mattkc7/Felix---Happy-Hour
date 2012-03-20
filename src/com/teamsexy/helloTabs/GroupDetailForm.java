package com.teamsexy.helloTabs;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class GroupDetailForm extends Activity { 
	
	String spotId = null;
	EditText name = null;
	
	GroupHelper helper=null;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.group_detail_form);
		
		helper = new GroupHelper(this);
        
        name=(EditText)findViewById(R.id.name); 
        
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
				helper.insert(name.getText().toString()); //, address.getText().toString(), type);
			} else {
				helper.update(spotId, name.getText().toString()); //, address.getText().toString(), type);
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
	
	@Override
	public void onActivityResult(int reqCode, int resultCode, Intent data) {
	  super.onActivityResult(reqCode, resultCode, data);

	  switch (reqCode) {
	    case (1) :
	      if (resultCode == Activity.RESULT_OK) {
	        Uri contactData = data.getData();
	        Cursor c =  managedQuery(contactData, null, null, null, null);
	        if (c.moveToFirst()) {
	          String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
	          
	          System.out.println(name);
	        
	        }
	      }
	      break;
	  }
	}
}