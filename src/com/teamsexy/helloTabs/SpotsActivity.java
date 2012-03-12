package com.teamsexy.helloTabs;

import java.util.ArrayList;
import java.util.List;
import android.util.Log;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;


public class SpotsActivity extends ListActivity {
    /** Called when the activity is first created. */
	Cursor model = null;
	SpotHelper helper = null;
	SpotAdapter adapter = null;

	EditText name=null;
	EditText address=null;
	RadioGroup types=null;
	
	public final static String ID_EXTRA="apt.tutorial._ID";
	
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.spots_main);

        //make the save button green
        //save.getBackground().setColorFilter(new LightingColorFilter(0xFF00FF00, 0xFFAAFFFF));
        
        helper= new SpotHelper(this);
        model=helper.getAll();
        startManagingCursor(model);
        adapter = new SpotAdapter(model);
        setListAdapter(adapter);
        
        
    }
	
	@Override
	public void onDestroy(){
		super.onDestroy();
		helper.close(); 
	}

	  @Override
	public void onListItemClick(ListView list, View view, int position, long id) {
		Intent i = new Intent(SpotsActivity.this, DetailForm.class);

		i.putExtra(ID_EXTRA, String.valueOf(id));
		startActivity(i);
	}
	  
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		Log.w("@@@@", "inflating menu");
		new MenuInflater(this).inflate(R.menu.option, menu);
		Log.w("@@@@", "menu inflated");
		return (super.onCreateOptionsMenu(menu));
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Log.w("@@@@", "entered onOptionsItemSelected");
		if (item.getItemId() == R.id.newSpot) {
			Log.w("@@@@", "did it work now?");
			startActivity(new Intent(SpotsActivity.this, DetailForm.class));
			return true;
		}
		return (super.onOptionsItemSelected(item));
	}
	
	class SpotAdapter extends CursorAdapter {
		SpotAdapter(Cursor c) {
			super(SpotsActivity.this, c);
		}
	
		@Override
		public void bindView(View row, Context ctxt, Cursor c) {
			SpotHolder holder=(SpotHolder)row.getTag();
			holder.populateFrom(c, helper); 
		}
		
		@Override
		public View newView(Context ctxt, Cursor c,ViewGroup parent) { 
			LayoutInflater inflater=getLayoutInflater();
			View row=inflater.inflate(R.layout.row, parent, false);
			SpotHolder holder=new SpotHolder(row);
			row.setTag(holder);
		    return(row);
		  }
	} // end of class SpotAdapter
	
	
	static class SpotHolder {
		private TextView name = null;
		private TextView address = null;
		private ImageView icon = null;

		SpotHolder(View row) {
			name = (TextView)row.findViewById(R.id.title);
			address = (TextView) row.findViewById(R.id.address);
			icon = (ImageView) row.findViewById(R.id.icon);
		}

		void populateFrom(Cursor c, SpotHelper helper) {
			name.setText(helper.getName(c));
			address.setText(helper.getAddress(c));
			
			if (helper.getType(c).equals("happy_hr")) {
				icon.setImageResource(R.drawable.happy_hr_spot);
			} else {
				icon.setImageResource(R.drawable.my_spot);
			}
		}
	} //end of SpotHolder class
}


