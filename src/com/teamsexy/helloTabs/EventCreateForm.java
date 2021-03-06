package com.teamsexy.helloTabs;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

public class EventCreateForm extends Activity { 

	String eventID = null;
	EditText name = null;

	EventsHelper helper = null;
	GroupHelper groupHelper = null;
	SpotHelper spotHelper = null;

	public final static String ID_EXTRA="apt.tutorial._ID";

	//date picker
	Button mPickDate;
	int mYear;
	int mMonth;
	int mDay;

	//time picker
	Button mPickTime;
	int mHour;
	int mMin;

	//variables to store the selected spot, time, date, and group
	String selectedSpot, selectedTime, selectedDate, selectedGroup;

	static final int TIME_DIALOG_ID=1;
	static final int DATE_DIALOG_ID=0;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mc_event_detail_form);
		
		// Initialize database helpers
		groupHelper = new GroupHelper(this);
		spotHelper = new SpotHelper(this);
		
		// Spinner to select a group
		// TODO Selecting multiple groups in spinner
		Spinner groupSelectSpinner = (Spinner)findViewById(R.id.select_groups);
		final ArrayList<EventSpinnerContainer> selectGroup = buildGroupSpinner();
		ArrayAdapter<EventSpinnerContainer> groupAdapter =
				new ArrayAdapter<EventSpinnerContainer>( 
						this,
						android.R.layout.simple_spinner_item,
						selectGroup );
		groupAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		groupSelectSpinner.setAdapter(groupAdapter);
		groupSelectSpinner.setOnItemSelectedListener(
				new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> parent, 
							View view, int pos, long id) {
						
						selectedGroup = selectGroup.get(pos).getSpinnerText();
						Toast.makeText(parent.getContext(),
								"Invite " + selectGroup.get(pos).getSpinnerText(),
								Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}
				});

		// Spinner to select a spot
		Spinner spotSelectSpinner = (Spinner)findViewById(R.id.select_spot);
		final ArrayList<EventSpinnerContainer> selectSpot = buildSpotSpinner();
		ArrayAdapter<EventSpinnerContainer> spotAdapter =
				new ArrayAdapter<EventSpinnerContainer>( 
						this,
						android.R.layout.simple_spinner_item,
						selectSpot );
		spotAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		spotSelectSpinner.setAdapter(spotAdapter);
		spotSelectSpinner.setOnItemSelectedListener(
				new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> parent, 
							View view, int pos, long id) {

						selectedSpot = selectSpot.get(pos).getSpinnerText();
						Toast.makeText(parent.getContext(),
								"Location: " + selectSpot.get(pos).getSpinnerText(),
								Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}
				});
		
		//spinner to select a SPOT
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.list_of_spots, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		//button to select time
		mPickTime=(Button)findViewById(R.id.timePickerBtn);
		mPickTime.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				Log.d("@@@", "ready to show dialog");
				showDialog(TIME_DIALOG_ID);
				Log.d("@@@", "DONE: to show dialog");
			}
		});


		//button to select date
		mPickDate = (Button)findViewById(R.id.datePickerBtn);
		mPickDate.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				Log.d("@@@", "ready to show dialog");
				showDialog(DATE_DIALOG_ID);
				Log.d("@@@", "DONE: to show dialog");
			}
		});

		final Calendar c = Calendar.getInstance();
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);
		
		//set the default date to today
		selectedDate = String.valueOf(mMonth+1) + "-" + String.valueOf(mDay) + "-" + String.valueOf(mYear);

		helper = new EventsHelper(this);


		Button save = (Button)findViewById(R.id.create_event);
		save.setOnClickListener(onSave);

		eventID = getIntent().getStringExtra(EventCreateForm.ID_EXTRA);

		if (eventID != null){
			load();
		}
	}

	private ArrayList<EventSpinnerContainer> buildGroupSpinner () {
		// Initialize list of groups with a TBD group
		ArrayList<EventSpinnerContainer> groups = new ArrayList<EventSpinnerContainer>();
		groups.add(new EventSpinnerContainer( -1, "TBD"));
		
		// Add all existing groups in database to list of groups for spinner
		Cursor g = groupHelper.getAll();
		g.moveToFirst();
        while (g.isAfterLast() == false) {
        	groups.add(new EventSpinnerContainer( 
        			g.getInt(0), g.getString(1))); 
        	
       	    g.moveToNext();
        }
        g.close();
		
		return groups;
	}
	
	private ArrayList<EventSpinnerContainer> buildSpotSpinner () {
		// Initialize list of spots with a TBD group
		ArrayList<EventSpinnerContainer> spots = new ArrayList<EventSpinnerContainer>();
		spots.add(new EventSpinnerContainer( -1, "TBD"));
		
		// Add all existing spots in database to list of spots for spinner
		Cursor s = spotHelper.getAll();
		s.moveToFirst();
		while (s.isAfterLast() == false) {
			spots.add(new EventSpinnerContainer(
					s.getInt(0), s.getString(1)));
			s.moveToNext();
		}
		s.close();
		
		return spots;
	}
	
	protected Dialog onCreateDialog(int id){
		switch(id) {
		case DATE_DIALOG_ID:
			return new DatePickerDialog(this, mDateSetListener, mYear, mMonth, mDay);
		case TIME_DIALOG_ID:
			return new TimePickerDialog(this, mTimeSetListener, mHour, mMin, false);
		}
		return null;
	}

	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener(){
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			// TODO Auto-generated method stub
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;

			mPickDate.setText(new StringBuilder().append(mMonth+1).append("-").
					append(mDay).append("-").append(mYear).append(" "));
			selectedDate = (String) mPickDate.getText();
		}
	};

	private TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener(){
		@Override
		public void onTimeSet(TimePicker view, int hour, int min) {
			// TODO Auto-generated method stub
			mHour = hour % 12;
			mMin = min;

			String newMin = null;
			if(mMin <=9){
				newMin = Integer.toString(mMin);
				newMin = "0"+newMin;
			} else {
				newMin = Integer.toString(mMin);
			}

			mPickTime.setText(new StringBuilder().append(mHour).append(":").append(newMin).append(" "));
			selectedTime = (String)mPickTime.getText();
		}
	};

	public void onDestroy(){
		super.onDestroy();
		helper.close(); 
	}

	private void load() {
		Cursor c=helper.getById(eventID);

		c.moveToFirst();    
		name.setText(helper.getSpot(c));
	}

	private View.OnClickListener onSave = new View.OnClickListener() {
		public void onClick(View v) {


			if (eventID == null){
				helper.insert(selectedSpot, selectedTime, selectedDate, selectedGroup);
			} else {
				helper.update(eventID, selectedSpot, selectedTime, selectedDate, selectedGroup); 
			}

			finish();
		}
	};

	/**
	 * EventSpinnerContainer
	 * 
	 * Allows us to create an ArrayAdapter for a spinner without losing
	 * database data. 
	 */
	class EventSpinnerContainer {

		String spinnerText;
		Integer value;

		public EventSpinnerContainer ( Integer id, String display ) {
			this.value = id;
			this.spinnerText = display;
		}

		public String getSpinnerText() {
			return spinnerText;
		}

		public Integer getValue() {
			return value;
		}

		public String toString() {
			return spinnerText;
		}
	}
}