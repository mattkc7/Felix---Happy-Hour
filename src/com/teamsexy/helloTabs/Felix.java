package com.teamsexy.helloTabs;

import com.teamsexy.helloTabs.R;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TabHost;

public class Felix extends TabActivity {
    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Resources res = getResources(); // Resource object to get Drawables
        TabHost tabHost = getTabHost();  // The activity TabHost
        TabHost.TabSpec spec;  // Resusable TabSpec for each tab
        Intent intent;  // Reusable Intent for each tab

        // Create an Intent to launch an Activity for the tab (to be reused)
        intent = new Intent().setClass(this, EventsActivity.class);

        // Initialize a TabSpec for each tab and add it to the TabHost
        spec = tabHost.newTabSpec("wtf").setIndicator("Now!",
                          res.getDrawable(R.drawable.tab_one))
                      .setContent(intent);
        tabHost.addTab(spec);

        // Do the same for the other tabs
        intent = new Intent().setClass(this, GroupsActivity.class);
        spec = tabHost.newTabSpec("groups").setIndicator("Groups",
                          res.getDrawable(R.drawable.tab_two))
                      .setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, SpotsActivity.class);
        spec = tabHost.newTabSpec("spots").setIndicator("My Spots",
                          res.getDrawable(R.drawable.tab_three))
                      .setContent(intent);
        tabHost.addTab(spec);


        
        tabHost.setCurrentTab(0);
    }
    
    public boolean onCreateOptionsMenu(Menu menu){
    	MenuInflater inflater = getMenuInflater();
    	int tab = getTabHost().getCurrentTab();
    	
    	switch (tab){
	    	case 0: menu.clear();
	    			inflater.inflate(R.menu.now_options, menu);
	    			break;
	    	case 1: menu.clear();
	    			inflater.inflate(R.menu.group_options, menu);
	    			break;
	    	case 2: menu.clear();
	    			inflater.inflate(R.menu.spot_options, menu);
	    			break;

    	}
    	
    	return (super.onCreateOptionsMenu(menu));
    }
}