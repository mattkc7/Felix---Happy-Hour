package com.teamsexy.helloTabs;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SpotsActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textview = new TextView(this);
        textview.setText("This is the -SPOTS- tab");
        setContentView(textview);
    }
}