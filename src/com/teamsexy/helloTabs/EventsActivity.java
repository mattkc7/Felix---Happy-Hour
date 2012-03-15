package com.teamsexy.helloTabs;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;


public class EventsActivity extends ListActivity {

	/* Data management */
	Cursor model = null;
	EventsHelper helper = null;
	
	/* Notification Management */
	SMSnotifier notifier = null;

	/* Location related */
	private LocationManager locationManager;
	private String locationProvider;

	public final static String ID_EXTRA="apt.tutorial._ID";

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mc_events_main);

		notifier = new SMSnotifier(this);
		// Example use:
		// notifier.sendSMS("5556", "giggity");
	
	}

	@Override
	public void onDestroy(){
		super.onDestroy();
		//helper.close();
		//locationManager.removeUpdates(this);
	}


	@Override
	public void onListItemClick(ListView list, View view, int position, long id) {
		Intent i = new Intent(EventsActivity.this, NewEventForms.class);

		i.putExtra(ID_EXTRA, String.valueOf(id));
		System.out.println("about to start intent");
		startActivity(i);
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

}