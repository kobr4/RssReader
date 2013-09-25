package com.nicolasmy.rssreader.listener;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.EditText;

import com.nicolasmy.rssreader.RssReaderActivity;

public class AddButtonOnClickListener implements View.OnClickListener {
    public void onClick(View v) {
    	AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());

    	alert.setTitle("New RSS source");
    	//alert.setMessage("Message");

    	// Set an EditText view to get user input 
    	final EditText input = new EditText(v.getContext());
    	alert.setView(input);

    	alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
    	public void onClick(DialogInterface dialog, int whichButton) {
    	  String value = input.getText().toString();
    	  // Do something with value!
    	  RssReaderActivity.urls.add(value);
    	  }
    	});

    	alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
    	  public void onClick(DialogInterface dialog, int whichButton) {
    	    // Canceled.
    	  }
    	});

    	alert.show();
    }
}
