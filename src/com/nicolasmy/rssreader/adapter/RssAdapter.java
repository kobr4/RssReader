package com.nicolasmy.rssreader.adapter;

import java.util.ArrayList;

import com.nicolasmy.R;
import com.nicolasmy.rssreader.rss.RssSaxHandler;
import com.nicolasmy.rssreader.rss.RssSaxHandler.Entry;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class RssAdapter extends BaseAdapter {

	private ArrayList<Entry> entries;
	private LayoutInflater inflater;
	private Context context;
	public RssAdapter(Context context,ArrayList<Entry> entries) {
		inflater = LayoutInflater.from(context);
		this.entries = entries;
		this.context = context;
	}
	
	public int getCount() {
		 return this.entries.size();
	}

	public Object getItem(int position) {
		return this.entries.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		//if (convertView == null)
		{
			RssSaxHandler.Entry e = (RssSaxHandler.Entry)this.entries.get(position);
			View v = inflater.inflate(R.layout.entry, null);
			TextView tv;
			tv = (TextView)v.findViewById(R.id.tvTitre);
			tv.setText(e.getTitle());
			
			if (e.getFavicon() != null)
			{
			ImageView ig;
			ig = (ImageView)v.findViewById(R.id.ivFavicon);
			ig.setImageBitmap(e.getFavicon());
			}
			return v;
		}
		/*
		else
		{
			return convertView;
		}
		*/
	}

}
