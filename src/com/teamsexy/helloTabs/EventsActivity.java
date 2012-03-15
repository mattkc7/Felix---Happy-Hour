package com.teamsexy.helloTabs;

import java.util.Random;

import com.teamsexy.helloTabs.SpotsActivity.SpotAdapter;
import com.teamsexy.helloTabs.SpotsActivity.SpotHolder;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class EventsActivity extends ListActivity {

	/* Data management */
	Cursor model = null;
	EventsHelper helper = null;
	EventAdapter adapter = null;
	/* Notification Management */
	SMSnotifier notifier = null;
	FelixGeofenceManager geomanager = null;

	/* Location related */
	private LocationManager locationManager;
	private String locationProvider;

	public final static String ID_EXTRA="apt.tutorial._ID";

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mc_events_main);

		notifier = new SMSnotifier(this);
		geomanager = new FelixGeofenceManager(this);
		// Example use:
		// notifier.sendSMS("5556", "giggity");
		
		helper= new EventsHelper(this);
        model=helper.getAll();
        startManagingCursor(model);
        adapter = new EventAdapter(model);
        setListAdapter(adapter);
	
	}

	@Override
	public void onDestroy(){
		super.onDestroy();
		//helper.close();
		//locationManager.removeUpdates(this);
	}
	
	@Override
	public void onPause(){
		super.onPause();
		geomanager.onActivityPause();
	}
	
	@Override
	public void onResume(){
		super.onResume();
		geomanager.onActivityResume();
	}

	@Override
	public void onListItemClick(ListView list, View view, int position, long id) {
//		Intent i = new Intent(EventsActivity.this, NewEventForms.class);
//
//		i.putExtra(ID_EXTRA, String.valueOf(id));
//		System.out.println("about to start intent");
//		startActivity(i);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		new MenuInflater(this).inflate(R.menu.now_options, menu);
		return (super.onCreateOptionsMenu(menu));
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.new_event) {
			startActivity(new Intent(EventsActivity.this, NewEventForms.class));
			return true;
		} else if (item.getItemId() == R.id.settings){
			Log.d("about to enter settingsactivity.java", "@@@");
			startActivity(new Intent(EventsActivity.this, SettingsActivity.class));
			return true;
		}
		return (super.onOptionsItemSelected(item));
	}
	
	//holder + adapter classes
	class EventAdapter extends CursorAdapter {
		EventAdapter(Cursor c) {
			super(EventsActivity.this, c);
		}
	
		@Override
		public void bindView(View row, Context ctxt, Cursor c) {
			EventHolder holder=(EventHolder)row.getTag();
			holder.populateFrom(c, helper); 
		}
		
		@Override
		public View newView(Context ctxt, Cursor c,ViewGroup parent) { 
			LayoutInflater inflater=getLayoutInflater();
			View row=inflater.inflate(R.layout.other_event_row, parent, false);
			EventHolder holder=new EventHolder(row);
			row.setTag(holder);
		    return(row);
		  }
	} // end of class SpotAdapter
	
	
	static class EventHolder {
		private TextView spotTime = null;
		private TextView groupDate = null;
		private ImageView icon = null;

		EventHolder(View row) {
			spotTime = (TextView)row.findViewById(R.id.spot_and_time);
			//groupDate = (TextView) row.findViewById(R.id.group_and_date);
			icon = (ImageView) row.findViewById(R.id.icon);
		}

		void populateFrom(Cursor c, EventsHelper helper) {
			String myBuffer = helper.getSpot(c);
			Random hour = new Random();
			int hr = hour.nextInt(7) + 1;
			myBuffer = myBuffer + " @ " + String.valueOf(hr) + "pm";
			spotTime.setText(myBuffer);
			//groupDate.setText(myBuffer);
			
//			if (helper.getType(c).equals("happy_hr")) {
//				icon.setImageResource(R.drawable.happy_hr_spot);
//			} else {
//				icon.setImageResource(R.drawable.my_spot);
//			}
		}
	} //end of SpotHolder class

}