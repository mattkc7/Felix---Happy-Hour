package com.teamsexy.helloTabs;

import android.app.ListActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class GroupMembersView extends ListActivity {
	  TextView selection;
	  private static final String[] items={"Jack Doha", "Billy Chrystal", "Jay Leno",
		  "Martin Shelton", "Pablo Winograd", "David Sun"};
	  
	  @Override
	  public void onCreate(Bundle icicle) {
	    super.onCreate(icicle);
	    setContentView(R.layout.group_memberslist);
	    setListAdapter(new IconicAdapter());
	    //selection=(TextView)findViewById(R.id.selection);
	  }
	  
	  public void onListItemClick(ListView parent, View v,
	                              int position, long id) {
	    selection.setText(items[position]);
	  }
	  
	  class IconicAdapter extends ArrayAdapter<String> {
	    IconicAdapter() {
	      super(GroupMembersView.this, R.layout.group_members_row, R.id.label, items);
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
