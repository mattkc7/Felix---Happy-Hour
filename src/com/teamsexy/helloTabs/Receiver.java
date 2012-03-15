package com.teamsexy.helloTabs;

import android.content.Context;
import android.location.Location;

import com.geoloqi.android.sdk.LQTracker.LQTrackerProfile;
import com.geoloqi.android.sdk.receiver.LQBroadcastReceiver;

/**
 * <p>This is a sample BroadcastReceiver implementation that
 * extends from {@link LQBroadcastReceiver}. This implementation
 * is designed to highlight how to consume broadcast intents and
 * take action on the messages received.</p>
 * 
 * Adapted slightly by genia
 * @author Tristan Waddington
 */
public class Receiver extends LQBroadcastReceiver {
    public static final String TAG = "Receiver";

    @Override
    public void onTrackerProfileChanged(Context context,
                    LQTrackerProfile oldProfile, LQTrackerProfile newProfile) {
        try {
            OnTrackerProfileChangedListener listener = (OnTrackerProfileChangedListener) context;
            listener.onTrackerProfileChanged(oldProfile, newProfile);
        } catch (ClassCastException e) {
            // The broadcast receiver is running with a Context that
            // does not implement OnTrackerProfileChangedListener. If your activity
            // has implemented the interface, then this generally means
            // that the receiver is running in a global context and
            // is not bound to any particular activity.
        }
    }

    @Override
    public void onLocationChanged(Context context, Location location) {
        try {
            OnLocationChangedListener listener = (OnLocationChangedListener) context;
            listener.onLocationChanged(location);
        } catch (ClassCastException e) {
            // The broadcast receiver is running with a Context that
            // does not implement OnLocationChangedListener. If your activity
            // has implemented the interface, then this generally means
            // that the receiver is running in a global context and
            // is not bound to any particular activity.
        }
    }

    @Override
    public void onLocationUploaded(Context context, int count) {
        try {
            OnLocationUploadedListener listener = (OnLocationUploadedListener) context;
            listener.onLocationUploaded(count);
        } catch (ClassCastException e) {
            // The broadcast receiver is running with a Context that
            // does not implement OnLocationUploadedListener. If your activity
            // has implemented the interface, then this generally means
            // that the receiver is running in a global context and
            // is not bound to any particular activity.
        }
    }

    public interface OnTrackerProfileChangedListener {
        public void onTrackerProfileChanged(LQTrackerProfile oldProfile,
                        LQTrackerProfile newProfile);
    }

    public interface OnLocationChangedListener {
        public void onLocationChanged(Location location);
    }

    public interface OnLocationUploadedListener {
        public void onLocationUploaded(int count);
    }
}
