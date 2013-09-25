package com.nicolasmy.rssreader.adapter;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class UrlAdapter extends BaseAdapter {
	private ArrayList<String> entries;
	LayoutInflater inflater;
	Context context;
	public UrlAdapter(Context context,ArrayList<String> entries) {
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
		if (convertView == null)
		{
			String s = (String)this.entries.get(position);
			TextView tv = new TextView(context);
			tv.setText(s);		
			return tv;
		}
		else
		{
			return convertView;
		}
	}

}
