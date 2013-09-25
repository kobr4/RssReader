package com.nicolasmy.rssreader.listener;

import com.nicolasmy.R;
import com.nicolasmy.rssreader.RssReaderActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class EditUrlListViewOnClickListener implements OnItemClickListener{
	private int element_position;
	public View active_view;
 	public void onItemClick(AdapterView<?> a, View v, int position, long id) {
 		element_position = position;
 		active_view = v;
 		String url = (String) a.getItemAtPosition(position);
    	AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());

    	alert.setTitle("Edit RSS source");
    	//alert.setMessage("Message");

    	// Set an EditText view to get user input 
    	final EditText input = new EditText(v.getContext());
    	input.setText(url);
    	alert.setView(input);

    	alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
    	public void onClick(DialogInterface dialog, int whichButton) {
    	  String value = input.getText().toString();
    	  // Do something with value!
    	  RssReaderActivity.urls.set(element_position,value);
    	  TextView tv = ((TextView)active_view);
    	  tv.setText(value);
    	  }
    	});

    	alert.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
    	public void onClick(DialogInterface dialog, int whichButton) {	
    		RssReaderActivity.urls.remove(element_position);
    		final ListView listview = (ListView)active_view.getParent();
    		listview.invalidateViews();
    	  }  
    	});
    		
    	alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
    	  public void onClick(DialogInterface dialog, int whichButton) {
    		  //
    	  }
    	});

    	alert.show();
	}
}
