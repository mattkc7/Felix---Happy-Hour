package com.teamsexy.helloTabs;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.Toast;

public class GroupEditActivity extends Activity {
	
	//private FelixDbAdapter groupDbHelper;
	//private ListView groupeditview;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.groupeditform);
        
        final EditText edittext1 = (EditText) findViewById(R.id.group_name);
        edittext1.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                    (keyCode == KeyEvent.KEYCODE_ENTER)) {
                  // Perform action on key press
                  Toast.makeText(GroupEditActivity.this, edittext1.getText(), Toast.LENGTH_SHORT).show();
                  return true;
                }
                return false;
            }
        });
        
        final EditText edittext2 = (EditText) findViewById(R.id.group_about);
        edittext2.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                    (keyCode == KeyEvent.KEYCODE_ENTER)) {
                  // Perform action on key press
                  Toast.makeText(GroupEditActivity.this, edittext2.getText(), Toast.LENGTH_SHORT).show();
                  return true;
                }
                return false;
            }
        });

        //String[] placeholder = {"[Group Name]", "[Group About]", "[Other TBD]", "[Button Placeholder]"}; 
        //setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, placeholder));
	}

}
