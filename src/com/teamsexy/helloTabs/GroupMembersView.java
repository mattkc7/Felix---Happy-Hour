package com.teamsexy.helloTabs;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class GroupMembersView extends ListActivity {

	TextView selection;
	ArrayList<String> members;
	GroupMemberHelper memberHelper;
	String groupId;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.group_memberslist);
		
		groupId = getIntent().getStringExtra(GroupsActivity.ID_EXTRA);
		memberHelper = new GroupMemberHelper(this);
		members = new ArrayList<String>();
		
		fetchGroupMembers();
		setListAdapter(new IconicAdapter());
		
		registerForContextMenu(getListView());
	}

	/*
	 * ==Context Menu tutorial==
	 * http://saigeethamn.blogspot.com/2011/05/context-menu-android-developer-tutorial.html
	 */
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo){
		super.onCreateContextMenu(menu, v, menuInfo);
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.contextmenu_for_grp_mmbrs, menu);
	}
	
	public boolean onContextItemSelected(MenuItem item) {
//	      AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
	      //String[] names = getResources().getStringArray(R.array.names);
	      switch(item.getItemId()) {
	      case R.id.delete:
	    	  //REALLY DELETE MEMBER
	    	  //How do we pass in which member to delete...?
	    	  reallyDeleteMember(item);
	            Toast.makeText(this, "Group member deleted =[",
	                        Toast.LENGTH_SHORT).show();
	            return true;
	      case R.id.ringWraiths:
	    	  Toast.makeText(this, "Group member destroyed >=]",
                      Toast.LENGTH_SHORT).show();
	      default:
	            return super.onContextItemSelected(item);
	      }
	}
	
	public void reallyDeleteMember(MenuItem item){
		
		
			new AlertDialog.Builder(this)
	        .setTitle( "Delete" )
	        .setMessage( "This member will be deleted." )
	        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface arg0, int arg1) {
	                //DELETE MEMBER HERE. 
	            	//helper.delete(...);

	            	
	                finish();
	            }
	        })
	        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface arg0, int arg1) {
	                //do nothing onclick of CANCEL
	                
	            }
	        }).setIcon(R.drawable.warning).show();
	}
	
	
	//menu options to ADD a new member
	public boolean onCreateOptionsMenu(Menu menu) {
		new MenuInflater(this).inflate(R.menu.grp_mmber_options, menu);
		return (super.onCreateOptionsMenu(menu));
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if (item.getItemId() == R.id.add_contact) {
			//add new member by going into the contacts menu
			
			Toast.makeText(GroupMembersView.this, "Getting a contact", Toast.LENGTH_SHORT).show();
			
			return true;
		}
		return (super.onOptionsItemSelected(item));
	}
	
	public void onListItemClick(ListView parent, View v,
			int position, long id) {
		Toast.makeText(GroupMembersView.this, "IN PROGRESS", Toast.LENGTH_SHORT).show();
		//selection.setText(members.get(position));
	}
	
	private void fetchGroupMembers () {
		
		Cursor c = memberHelper.getContactsByGroupId(groupId);
		c.moveToFirst();
		while (c.isAfterLast() == false) {
				
			members.add(c.getString(1));
			c.moveToNext();
		}
			
		c.close();		
	}
	
	class GroupMemberContainer {
		
		String displayName;
		String memberId;
		
		public GroupMemberContainer (String i, String n) {
			this.memberId = n;
			this.displayName = n;
		}
		
		public String getName () {
			return displayName;
		}
		
		public String getId () {
			return memberId;
		}
	}

	class IconicAdapter extends ArrayAdapter<String> {
		IconicAdapter() {
			super(GroupMembersView.this, R.layout.group_members_row, R.id.label, members);
		}

		public View getView(int position, View convertView,
				ViewGroup parent) {
			View row=super.getView(position, convertView, parent);
			ImageView icon=(ImageView)row.findViewById(R.id.icon);

			icon.setImageResource(R.drawable.person);

			return(row);
		}
	}
}
