package com.nicolasmy.rssreader.listener;

import com.nicolasmy.rssreader.rss.RssSaxHandler;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class UrlListViewOnClickListener  implements OnItemClickListener {
 	public void onItemClick(AdapterView<?> a, View v, int position, long id) {
		RssSaxHandler.Entry e = (RssSaxHandler.Entry)a.getItemAtPosition(position);
		Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(e.getLink()));
		v.getContext().startActivity(browserIntent);
	}
}
