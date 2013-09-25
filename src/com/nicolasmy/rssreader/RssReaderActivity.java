package com.nicolasmy.rssreader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.nicolasmy.R;
import com.nicolasmy.rssreader.adapter.RssAdapter;
import com.nicolasmy.rssreader.adapter.UrlAdapter;
import com.nicolasmy.rssreader.listener.AddButtonOnClickListener;
import com.nicolasmy.rssreader.listener.EditUrlListViewOnClickListener;
import com.nicolasmy.rssreader.listener.SaveButtonOnClickListener;
import com.nicolasmy.rssreader.listener.UrlListViewOnClickListener;
import com.nicolasmy.rssreader.rss.RssLoader;
import com.nicolasmy.rssreader.rss.RssSaxHandler.Entry;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;


public class RssReaderActivity extends Activity {
	public static ArrayList<String> urls = new ArrayList<String>();
	private RssLoader rssloader;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //urls.add("http://rss.slashdot.org/Slashdot/slashdot");
        //urls.add("http://www.pcinpact.com/include/news.xml");
        //urls.add("http://rss.lemonde.fr/c/205/f/3050/index.rss");
        
        /*
        loadUrls(this);
        rssloader = new RssLoader(urls);
        refreshRssFeeds();
 */
    }
    
    
    public static void loadUrls(Context context)
    {
    	File file = new File(context.getExternalFilesDir(null), "urls.xml");   	
    	
		try {
			ObjectInputStream os = new ObjectInputStream(new FileInputStream(file));
			
			try {
				urls = (ArrayList<String>)os.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (StreamCorruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
    }
    
    public static void saveUrls(Context context)
    {
    	File file = new File(context.getExternalFilesDir(null), "urls.xml");
    	
    	try {
    		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file));
			
			os.writeObject(RssReaderActivity.urls);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    
    }
    
    
    public void refreshRssFeeds()
    {
        ListView listlview = new ListView(this);
        try {
			rssloader.fetchRssFeed();
		} catch (ParserConfigurationException e) {
			makeAlertDialog("Error occured while configuring parser");
		} catch (SAXException e) {
			makeAlertDialog("Error occured while parsing RSS feed");
		} catch (IOException e) {
			makeAlertDialog("Error occured while reading RSS feed");
		}
        ArrayList<Entry> arraylist = rssloader.getEntries();
        RssAdapter rssadapter = new RssAdapter(this, arraylist);
        listlview.setAdapter(rssadapter);

        listlview.setOnItemClickListener(new UrlListViewOnClickListener());        
        
        setContentView(listlview);    	
    }
    
    public void makeAlertDialog(String message)
    {
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setMessage(message)
    	       .setCancelable(false)
    	       .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	        	   dialog.cancel();
    	           }
    	       });
    	AlertDialog alert = builder.create();
    	alert.show();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options, menu);
        return true;
    }    
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
        case R.id.configuration:
        	setContentView(R.layout.configuration);
        	final ListView listview = (ListView) findViewById(R.id.listView1);
        	UrlAdapter urladapter = new UrlAdapter(this,RssReaderActivity.urls);
        	listview.setAdapter(urladapter);
        	listview.setOnItemClickListener(new EditUrlListViewOnClickListener());
        
            final Button button = (Button) findViewById(R.id.button1);
            button.setOnClickListener(new AddButtonOnClickListener());        	
            
            final Button savebutton = (Button) findViewById(R.id.button3);
            savebutton.setOnClickListener(new SaveButtonOnClickListener());              
            return true;
        case R.id.refresh:
        	refreshRssFeeds();
        	return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }    
}