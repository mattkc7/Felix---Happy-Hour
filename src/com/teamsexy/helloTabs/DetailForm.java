package com.teamsexy.helloTabs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class DetailForm extends Activity { 
	
	String spotId = null;
	EditText name=null;
	EditText address=null;
	RadioGroup types=null;
	SpotHelper helper=null;
	
	// Location Management
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_form);
		
		
		helper = new SpotHelper(this);
        
        name=(EditText)findViewById(R.id.name); 
        address=(EditText)findViewById(R.id.addr); 
        types=(RadioGroup)findViewById(R.id.types);
        
        Button save = (Button)findViewById(R.id.save);
        save.setOnClickListener(onSave);
        
        Button findOnMap = (Button)findViewById(R.id.findOnMap);
        findOnMap.setOnClickListener(onMapFinder);
        
        spotId = getIntent().getStringExtra(SpotsActivity.ID_EXTRA);
        
        if (spotId != null){
        	load();
        }
        
	}
	
	public void onDestroy(){
		super.onDestroy();
		helper.close(); 
	}
	
	private void load() {
	    Cursor c=helper.getById(spotId);

	    c.moveToFirst();    
	    name.setText(helper.getName(c));
	    address.setText(helper.getAddress(c));
	    
	    if (helper.getType(c).equals("happy_hr")) {
	      types.check(R.id.happy_hr);
	    }
	    else {
	      types.check(R.id.my_spot);
	    }
	    
	    c.close();
	  }
	
	private View.OnClickListener onSave = new View.OnClickListener() {
		public void onClick(View v) {

			String type = null;

			switch (types.getCheckedRadioButtonId()) {
			case R.id.happy_hr:
				type ="happy_hr";
				break;
			case R.id.my_spot:
				type ="my_spot";
				break;
			}
			
			if (spotId == null){
				helper.insert(name.getText().toString(), address.getText().toString(), type);
			} else {
				helper.update(spotId, name.getText().toString(), address.getText().toString(), type);
			}
			
			finish();
		}
	};
	
	private View.OnClickListener onMapFinder = new View.OnClickListener() {
		public void onClick(View v) {
			
			Intent i = new Intent(DetailForm.this, FelixMapActivity.class);
			startActivity(i);
			
		}
	};
	
	public boolean onCreateOptionsMenu(Menu menu) {
		new MenuInflater(this).inflate(R.menu.spot_edit_options, menu); //previously option
		return (super.onCreateOptionsMenu(menu));
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.delete_spot) {
			//startActivity(new Intent(SpotsActivity.this, DetailForm.class));
			reallyDeleteSpot();
			return true;
		}
		return (super.onOptionsItemSelected(item));
	}
	
	public void reallyDeleteSpot(){
		new AlertDialog.Builder(this)
        .setTitle( "Delete" )
        .setMessage( "This spot will be deleted." )
        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
            	
            	helper.delete(spotId);
         
            	Toast.makeText(getBaseContext(), "Spot deleted", Toast.LENGTH_SHORT).show();
                finish();
            }
        })
        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                //do nothing onclick of CANCEL
                Toast.makeText(getBaseContext(), "You touched CANCEL", Toast.LENGTH_SHORT).show();
            }
        }).setIcon(R.drawable.warning).show();
	}
}