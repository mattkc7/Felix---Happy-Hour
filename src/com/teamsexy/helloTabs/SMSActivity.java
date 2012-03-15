package com.teamsexy.helloTabs;

import java.util.List;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;

/**
 * SMS Activity - SMS Messaging Notifications
 * @author genia
 *
 * This activity is designed to send notifications by Text to some given number.
 * It can be intiated from another activity as follows:
 * 
 * 
 * 
 * If using a button, remember to do the following:
 *** Add the button to your desired layout
 *** Create an View.OnClickListenerView for the button
 */
public class SMSActivity extends Activity {
	
	 private static final String TAG = "SMSActivity";
	 
	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);

		 SmsManager sms = SmsManager.getDefault();
		 String message = "I am a test message, gangsta";
		 String recipient = "7654914355";
		 PendingIntent pi = PendingIntent.getActivity(SMSActivity.this, 0,
				 new Intent(this, EventsActivity.class), 0);
		 sms.sendTextMessage(recipient, null, message, pi, null);

	 }	 
}
