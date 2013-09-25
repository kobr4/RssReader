package com.nicolasmy.rssreader.listener;

import com.nicolasmy.rssreader.RssReaderActivity;

import android.view.View;

public class SaveButtonOnClickListener  implements View.OnClickListener {

	public void onClick(View v) {
		RssReaderActivity.saveUrls(v.getContext());
	}

}
