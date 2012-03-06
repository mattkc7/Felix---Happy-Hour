package com.teamsexy.helloTabs;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class EventEditActivity extends Activity {

	private FelixDbAdapter eventDbHelper;
	private ListView eventeditview;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventeditform);
        
        final EditText edittext1 = (EditText) findViewById(R.id.event_name);
        edittext1.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                    (keyCode == KeyEvent.KEYCODE_ENTER)) {
                  // Perform action on key press
                  Toast.makeText(EventEditActivity.this, edittext1.getText(), Toast.LENGTH_SHORT).show();
                  return true;
                }
                return false;
            }
        });
        
        final EditText edittext2 = (EditText) findViewById(R.id.event_about);
        edittext2.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                    (keyCode == KeyEvent.KEYCODE_ENTER)) {
                  // Perform action on key press
                  Toast.makeText(EventEditActivity.this, edittext2.getText(), Toast.LENGTH_SHORT).show();
                  return true;
                }
                return false;
            }
        });
        
        //String[] placeholder = {"[Edit Event Title]", "[Edit Event About]", "[Event Location]", "[Event Date/Time]", "[Button]"};
        //setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, placeholder));
	}
}
