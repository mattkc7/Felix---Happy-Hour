package com.teamsexy.helloTabs;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.SmsManager;
import android.widget.Toast;

/**
 * SMSnotifier
 * @author genia
 *
 * This class uses the Android telephony API to send a text. 
 * 
 * = Initialization =
 * In any Activity, declare a local SMSnotifier variable:
 * SMSnotifier notifier = null;
 * 
 * Initialize in the onCreate function with a Context like so:
 * notifier = new SMSnotifier(this);
 * 
 * Then, just call with:
 * notifier.sendSMS(number, message)
 * 
 * = Emulators =
 * Create a couple of emulators in the AVD Manager that match the level
 * of your desired API. For example, avdparty, avdparty2, avdparty3. When sending 
 * to an emulator, 5556 or 5558 recommended as the number. 
 * 
 * To run another emulator outside of Eclipse, go to the top-level directory of your
 * Android SDK, and find tools:
 * cd path/to/android/sdk/tools/
 * 
 * [First, you may want to start this app from within Eclipse...]
 * Run the emulator: ./emulator -avd [avdname]
 * 
 * Emulators each require 2 ports, so 5554-5555 is the first, then 5556-5557, etc.
 * Thus, send messages to even numbers and they should be received on the other emulator!
 */
public class SMSnotifier {

	private Context context;
	
	public SMSnotifier (Context ctx) {
		this.context = ctx;
	}
	
	public void sendSMS(String phoneNumber, String message)
	{        
		String SENT = "SMS_SENT";
		String DELIVERED = "SMS_DELIVERED";

		PendingIntent sentPI = PendingIntent.getBroadcast(context, 0,
				new Intent(SENT), 0);

		PendingIntent deliveredPI = PendingIntent.getBroadcast(context, 0,
				new Intent(DELIVERED), 0);

		//---when the SMS has been sent---
		context.registerReceiver(new BroadcastReceiver(){
			@Override
			public void onReceive(Context arg0, Intent arg1) {
				switch (getResultCode())
				{
				case Activity.RESULT_OK:
					Toast.makeText(context, "Notification sent", 
							Toast.LENGTH_SHORT).show();
					break;
				case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
					Toast.makeText(context, "Generic failure", 
							Toast.LENGTH_SHORT).show();
					break;
				case SmsManager.RESULT_ERROR_NO_SERVICE:
					Toast.makeText(context, "No service", 
							Toast.LENGTH_SHORT).show();
					break;
				case SmsManager.RESULT_ERROR_NULL_PDU:
					Toast.makeText(context, "Null PDU", 
							Toast.LENGTH_SHORT).show();
					break;
				case SmsManager.RESULT_ERROR_RADIO_OFF:
					Toast.makeText(context, "Radio off", 
							Toast.LENGTH_SHORT).show();
					break;
				}
			}
		}, new IntentFilter(SENT));

		//---when the SMS has been delivered---
		context.registerReceiver(new BroadcastReceiver(){
			@Override
			public void onReceive(Context arg0, Intent arg1) {
				switch (getResultCode())
				{
				case Activity.RESULT_OK:
					Toast.makeText(context, "SMS delivered", 
							Toast.LENGTH_SHORT).show();
					break;
				case Activity.RESULT_CANCELED:
					Toast.makeText(context, "SMS not delivered", 
							Toast.LENGTH_SHORT).show();
					break;                        
				}
			}
		}, new IntentFilter(DELIVERED));        

		SmsManager sms = SmsManager.getDefault();
		sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI);        
	}
}
