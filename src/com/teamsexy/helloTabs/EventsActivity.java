package com.teamsexy.helloTabs;

import java.util.Calendar;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
			startActivity(new Intent(EventsActivity.this, EventCreateForm.class));
			return true;
		} else if (item.getItemId() == R.id.settings){
			Log.d("about to enter settingsactivity.java", "@@@");
			startActivity(new Intent(EventsActivity.this, SettingsActivity.class));
			return true;
		}
		return (super.onOptionsItemSelected(item));
	}

	/* Event Coordination and Notification */
	public void notifyInvitation(String inviteText) {
		notifier.sendSMS("5556", "You're Invited! " + inviteText);
		notifier.sendSMS("5558", "You're Invited! " + inviteText);
	}
	
	public void notifyLeaving(String leavingText) {
		notifier.sendSMS("5556", "Heading out, see you soon. " + leavingText);
		notifier.sendSMS("5558", "Heading out, see you soon. " + leavingText);
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
			View row=inflater.inflate(R.layout.event_row, parent, false);
			EventHolder holder=new EventHolder(row);
			row.setTag(holder);
		    return(row);
		  }
	} // end of class SpotAdapter
	
	
	static class EventHolder {
		private TextView spotTime = null;
		private TextView groupDate = null;
		private ImageView icon = null;
		int m, d;

		EventHolder(View row) {
			spotTime = (TextView)row.findViewById(R.id.spot_and_time);
			groupDate = (TextView) row.findViewById(R.id.group_and_date);
			icon = (ImageView) row.findViewById(R.id.icon);
		}

		void populateFrom(Cursor c, EventsHelper helper) {
			String myBuffer = helper.getSpot(c) + " @ " + helper.getTime(c);
			//Random hour = new Random();
			//int hr = hour.nextInt(7) + 1;
			//myBuffer = myBuffer + " @ " + "5:05pm";
			
			icon.setImageResource(R.drawable.yellow_flag);
			spotTime.setText(myBuffer);
			
			if(helper.getDate(c) != null){
				m = parseMonth(helper.getDate(c));
				d = parseDay(helper.getDate(c));
			}
			
			final Calendar cc = Calendar.getInstance();
			int mMonth = cc.get(Calendar.MONTH)+1;
			int mDay = cc.get(Calendar.DAY_OF_MONTH);
			Log.d("@@@", String.valueOf(mMonth));
			Log.d("@@@", String.valueOf(mDay));
			Log.d("--", String.valueOf(m));
			Log.d("--", String.valueOf(d));
			
			//check if the event is in the past & delete it
			if(eventMonthIsOver(m, mMonth) && eventDayIsOver(d, mDay)){
				//helper.delete(..., time, date, group);
			}
			
			//if the event is TODAY, mark it with a RED FLAG. Future events will be YELLOW_Flags
			if ((m == mMonth) && (d == mDay)){
				icon.setImageResource(R.drawable.red_flag);
			}
			myBuffer = helper.getGroup(c) + " - " + String.valueOf(m)+"/"+String.valueOf(d);//helper.getDate(c);
			groupDate.setText(myBuffer);
			
			
		}
		
		public boolean eventMonthIsOver(int eventM, int currentM){
			return eventM <= currentM;
		}
		
		public boolean eventDayIsOver(int eventD, int currentD){
			return eventD < currentD;
		}
		
		public static int parseMonth(String s){
			int hyphen1 = s.indexOf("-");
			  String month = s.substring(0, hyphen1);
			  int f = Integer.valueOf(month);
			  return f;
		}
		
		public static int parseDay(String s){
			int hyphen1 = s.indexOf("-");
			int hyphen2 = s.indexOf("-", hyphen1+1);
			String day = s.substring(hyphen1+1, hyphen2);
			int f = Integer.valueOf(day);
			return f;
		}
	} //end of SpotHolder class
}
