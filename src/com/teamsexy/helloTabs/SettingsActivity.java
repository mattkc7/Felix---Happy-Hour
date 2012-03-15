package com.teamsexy.helloTabs;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;

public class SettingsActivity extends ListActivity{
	
	//CheckBox cb;
	private static final String[] items={
		"Notify friends when I leave a spot.", 
		"Notify friends when I am X distance away.", 
		"Notify ME when friends have left for an event.",
        "Notify ME for all new events every 5 hours.", 
        "Notify ME when a friend adds me.",
        };
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settingscheckboxes); //settingscheck.xml is hardcoded checkboxes
        
        setListAdapter(new ArrayAdapter<String>(this,
        		android.R.layout.simple_list_item_multiple_choice, items));
        		
//        cb=(CheckBox)findViewById(R.id.check);
//        cb.setOnCheckedChangeListener(this);
//        TextView textview = new TextView(this);
//        textview.setText("This is the @settings@ tab");
//        setContentView(textview);
        
    }
    
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
    	
//    	if (isChecked){
//    		cb.setText("huh?");
//    	} else {
//    		cb.setText("NAY!");
//    	}

    }
}