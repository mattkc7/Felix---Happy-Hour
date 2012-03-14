package com.teamsexy.helloTabs;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class DetailForm extends Activity { 
	
	String spotId = null;
	EditText name=null;
	EditText address=null;
	RadioGroup types=null;
	SpotHelper helper=null;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_form);
		
		
		helper = new SpotHelper(this);
        
        name=(EditText)findViewById(R.id.name); 
        address=(EditText)findViewById(R.id.addr); 
        types=(RadioGroup)findViewById(R.id.types);
        
        Button save = (Button)findViewById(R.id.save);
        save.setOnClickListener(onSave);
        
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
}