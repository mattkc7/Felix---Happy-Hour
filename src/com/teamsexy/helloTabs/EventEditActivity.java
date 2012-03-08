package com.teamsexy.helloTabs;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class EventEditActivity extends Activity {

	// Data management
	private FelixDbAdapter eventDbHelper;
	private ListView eventeditview;
	
	// Date Input
	private TextView dateDisplay;
	private TextView timeDisplay;
	private Button pickDate;
	private Button pickTime;
	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	static final int DATE_DIALOG_ID = 0;
	static final int TIME_DIALOG_ID = 1;
	
	private TimePickerDialog.OnTimeSetListener timeSetListener = 
    		new TimePickerDialog.OnTimeSetListener() {
        		public void onTimeSet(TimePicker view, int hourOfDay, int min) {
        			hour = hourOfDay;
        			minute = min;
        			updateTimeDisplay();
        		}
    		};
	private DatePickerDialog.OnDateSetListener dateSetListener = 
			new DatePickerDialog.OnDateSetListener() {
				public void onDateSet(DatePicker view, int yr, 
                              int monthOfYear, int dayOfMonth) {
					year = yr;
					month = monthOfYear;
					day = dayOfMonth;
					updateDateDisplay();
				}
    		}; 
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventeditform);
        
        // Edit Event Nmae
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
        
        // Edit Event About Field
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
        
        // Edit Event Location - Placeholder
        
       // Edit Event Date
        // capture our View elements
        dateDisplay = (TextView) findViewById(R.id.dateDisplay);
        pickDate = (Button) findViewById(R.id.pickDate);

        // add a click listener to the button
        pickDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });

        // get the current date
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        // display the current date (this method is below)
        updateDateDisplay();
        
        // Edit Event Time
        // capture our View elements
        timeDisplay = (TextView) findViewById(R.id.timeDisplay);
        pickTime = (Button) findViewById(R.id.pickTime);

        // add a click listener to the button
        pickTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(TIME_DIALOG_ID);
            }
        });

        // get the current time
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);

        // display the current date
        updateTimeDisplay();
	}
	
	private void updateDateDisplay() {
		dateDisplay.setText(
	            new StringBuilder()
	                    // Month is 0 based so add 1
	                    .append(month + 1).append("-")
	                    .append(day).append("-")
	                    .append(year).append(" "));
	}
	
	private void updateTimeDisplay () {
		timeDisplay.setText(
		        new StringBuilder()
		                .append(pad(hour)).append(":")
		                .append(pad(minute)));
	}
	
	/**
	 * Padding method for time display
	 * @param c
	 * @return
	 */
	private static String pad(int c) {
	    if (c >= 10)
	        return String.valueOf(c);
	    else
	        return "0" + String.valueOf(c);
	}
	
    protected Dialog onCreateDialog(int id) {
    	switch (id) {
    	case TIME_DIALOG_ID:
    		return new TimePickerDialog(this,
    				timeSetListener, hour, minute, false);
    	case DATE_DIALOG_ID:
    		return new DatePickerDialog(this,
    				dateSetListener,
    				year, month, day);
    	}
            	        
    	return null;
    }
}
