package com.teamsexy.helloTabs;

import java.util.ArrayList;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class GroupMembersView extends ListActivity {

	TextView selection;
	ArrayList<String> members;
	GroupMemberHelper memberHelper;
	Long groupId;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.group_memberslist);
		
		groupId = getIntent().getLongExtra(GroupsActivity.ID_EXTRA, -1L);
		memberHelper = new GroupMemberHelper(this);
		members = new ArrayList<String>();
		members.add("Test 1");
		members.add("Test 2");
		
		fetchGroupMembers();
		setListAdapter(new IconicAdapter());
	}

	public void onListItemClick(ListView parent, View v,
			int position, long id) {
		
		selection.setText(members.get(position));
	}
	
	private void fetchGroupMembers () {
		/*if (groupId != -1L) {
			Cursor c = memberHelper.getContactsByGroupId(groupId.toString());
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				
				// TODO members need IDs to be removable from group - 
				// need to create a MemberEntry class for this
				
				members.add(c.getString(1));
				
				c.moveToNext();
			}
			
			c.close();
		}*/
		
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
