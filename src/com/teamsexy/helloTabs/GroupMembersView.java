package com.teamsexy.helloTabs;

import java.util.ArrayList;

import android.app.ListActivity;
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

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.group_memberslist);
		
		members = getGroupMembers();
		setListAdapter(new IconicAdapter());
		//selection=(TextView)findViewById(R.id.selection);
	}

	public void onListItemClick(ListView parent, View v,
			int position, long id) {
		
		// Should be members get position name...
		selection.setText(members.get(position));
	}
	
	private ArrayList<String> getGroupMembers () {
		ArrayList<String> members = new ArrayList<String>();
		members.add("Member 1");
		members.add("Member 2");
		members.add("Martin Shelton");
		return members;
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
