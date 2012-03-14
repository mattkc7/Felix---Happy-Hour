package com.teamsexy.helloTabs;

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
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class NewEventForms extends Activity { 
	
	String spotId = null;
	EditText name=null;
	
	EventsHelper helper=null;
	
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
	
	static final int TIME_DIALOG_ID=1;
	static final int DATE_DIALOG_ID=0;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mc_event_detail_form);
		
		//the spinner to select your group(s). How can we select multple groups?
				ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.list_of_groups, 
																				android.R.layout.simple_spinner_item);
				adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				Spinner s2 = (Spinner) findViewById(R.id.select_groups);
				s2.setAdapter(adapter2);
				s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
					 
					@Override
					public void onItemSelected(AdapterView<?> adap, View v, int i, long lng) {
						//do something here
						Log.d("@..@@", adap.getItemAtPosition(i).toString());
						Toast.makeText(adap.getContext(), adap.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();
					}
					 
					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						//do something else
					}
				});
		
		
		//spinner to select a SPOT
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.list_of_spots, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		Spinner s = (Spinner) findViewById(R.id.select_spot);
		s.setAdapter(adapter);
		s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
			 
			@Override
			public void onItemSelected(AdapterView<?> adap, View v, int i, long lng) {
				//do something here
				Log.d("@@@", adap.getItemAtPosition(i).toString());
				Toast.makeText(adap.getContext(), adap.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();
			}
			 
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				//do something else
			}
		});
		
		
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

		
		helper = new EventsHelper(this);
        

        //Button save = (Button)findViewById(R.id.save);
        //save.setOnClickListener(onSave);
        
        spotId = getIntent().getStringExtra(NewEventForms.ID_EXTRA);
        
        if (spotId != null){
        	load();
        }
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
		}
	};
	
	public void onDestroy(){
		super.onDestroy();
		helper.close(); 
	}
	
	private void load() {
	    Cursor c=helper.getById(spotId);

	    c.moveToFirst();    
	    name.setText(helper.getName(c));
//	    address.setText(helper.getAddress(c));
//	    
//	    if (helper.getType(c).equals("happy_hr")) {
//	      types.check(R.id.happy_hr);
//	    }
//	    else {
//	      types.check(R.id.my_spot);
//	    }
	    
	    c.close();
	  }
	
	private View.OnClickListener onSave = new View.OnClickListener() {
		public void onClick(View v) {

//			String type = null;
//
//			switch (types.getCheckedRadioButtonId()) {
//			case R.id.happy_hr:
//				type ="happy_hr";
//				break;
//			case R.id.my_spot:
//				type ="my_spot";
//				break;
//			}
			
			if (spotId == null){
				helper.insert(name.getText().toString()); //, address.getText().toString(), type);
			} else {
				helper.update(spotId, name.getText().toString()); //, address.getText().toString(), type);
			}
			
			finish();
		}
	};
}