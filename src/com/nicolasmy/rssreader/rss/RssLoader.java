package com.nicolasmy.rssreader.rss;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.nicolasmy.rssreader.rss.RssSaxHandler.Entry;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class RssLoader {
	private ArrayList<String> urls;
	private ArrayList<Entry> entries = new ArrayList<Entry>();
	private Bitmap faviconImg;
	public RssLoader(ArrayList<String> urls)
	{
		this.urls = urls;
	}
	
	public void setUrls(ArrayList<String> urls)
	{
		this.urls = urls;
	}
	
	private Bitmap loadFavicon(String url)
	{
		Bitmap favicon = null;
		
        URL myFileUrl =null;          
        try {
             myFileUrl= new URL(url);
        } catch (MalformedURLException e) {
             return null;
        }
        try {
             HttpURLConnection conn= (HttpURLConnection)myFileUrl.openConnection();
             conn.setDoInput(true);
             conn.connect();
             InputStream is = conn.getInputStream();
             
             byte data[] = new byte[10000]; 
             int read = 0;
             int offset = 0;
             while ((read = is.read(data,offset, data.length-offset)) > 0)
             {
               offset += read;
             }
             favicon = BitmapFactory.decodeByteArray(data, 0, offset);
             
             //favicon = BitmapFactory.decodeStream(is);
        } catch (IOException e) {
             return null;
        }
        
		return favicon;
	}
	
	public void fetchRssFeed() throws ParserConfigurationException, SAXException, IOException
	{
		entries.clear();
		for (int i = 0;i < urls.size();i++)
		{

	
        // On passe par une classe factory pour obtenir une instance de sax
        SAXParserFactory fabrique = SAXParserFactory.newInstance();
        SAXParser parseur = null;
        ArrayList<Entry> localentries = null;

        parseur = fabrique.newSAXParser();
        
        URL url = null;
        url = new URL(this.urls.get(i));
 
		Bitmap favicon = loadFavicon("http://"+url.getHost().replaceAll("rss", "www")+"/favicon.ico");        
        
        DefaultHandler handler = new RssSaxHandler();

        InputStream input = url.openStream();
        if(input==null)
           Log.e("erreur android","null");
        else{
           parseur.parse(input, handler);
           localentries = ((RssSaxHandler) handler).getData();
           	if (favicon != null)
           	{
        		for (int j = 0;j < localentries.size();j++)
        		{
        			Entry e = localentries.get(j);
        			e.setFavicon(favicon);
        		}
           	}
          }
        entries.addAll(localentries);
		}
        
	}
	
	public ArrayList<Entry> getEntries()
	{
		return this.entries;
	}
}
