package com.nicolasmy.rssreader.rss;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.graphics.Bitmap;
public class RssSaxHandler extends DefaultHandler {
	public class Entry
	{
		private String title;
		private String link;
		private String pubDate;
		private String creator;
		private String description;
		private Bitmap favicon;
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getLink() {
			return link;
		}
		public void setLink(String link) {
			this.link = link;
		}
		public String getPubDate() {
			return pubDate;
		}
		public void setPubDate(String pubdate) {
			this.pubDate = pubdate;
		}
		public String getCreator() {
			return creator;
		}
		public void setCreator(String creator) {
			this.creator = creator;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		
		public Bitmap getFavicon() {
			return favicon;
		}
		public void setFavicon(Bitmap favicon) {
			this.favicon = favicon;
		}		
	}
	
    private final String ITEM = "item";
    private final String TITLE = "title";
    private final String LINK = "link";
    private final String PUBDATE = "pubDate";
    private final String CREATOR = "creator";
    private final String DESCRIPTION = "description";
    private ArrayList<Entry> entries;
    private boolean inItem;
    private Entry currentFeed;
    private StringBuffer buffer;
    @Override
    public void processingInstruction(String target, String data) throws SAXException {
        super.processingInstruction(target, data);
    }
    public RssSaxHandler() {
        super();
    }
    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        entries = new ArrayList<Entry>();
    }
    @Override
    public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
        buffer = new StringBuffer();
        if (localName.equalsIgnoreCase(ITEM)){
            this.currentFeed = new Entry();
            inItem = true;
        }
        if (localName.equalsIgnoreCase(TITLE)){
            // Nothing to do
        }
        if (localName.equalsIgnoreCase(LINK)){
            // Nothing to do
        }
        if (localName.equalsIgnoreCase(PUBDATE)){
            // Nothing to do
        }
        if (localName.equalsIgnoreCase(CREATOR)){
            // Nothing to do
        }
        if(localName.equalsIgnoreCase(DESCRIPTION)){
            // Nothing to do
        }
    }
    @Override
    public void endElement(String uri, String localName, String name) throws SAXException {
        if (localName.equalsIgnoreCase(TITLE)){
            if(inItem){
                this.currentFeed.setTitle(buffer.toString());
                buffer = null;
            }
        }
        if (localName.equalsIgnoreCase(LINK)){
            if(inItem){
                this.currentFeed.setLink(buffer.toString());
                buffer = null;
            }
        }
        if (localName.equalsIgnoreCase(PUBDATE)){
            if(inItem){
                this.currentFeed.setPubDate(buffer.toString());
                buffer = null;
            }
        }
        if (localName.equalsIgnoreCase(CREATOR)){
            if(inItem){
                this.currentFeed.setCreator(buffer.toString());
                buffer = null;
            }
        }
        if(localName.equalsIgnoreCase(DESCRIPTION)){
            if(inItem){
                this.currentFeed.setDescription(buffer.toString());
                buffer = null;
            }
        }
        if (localName.equalsIgnoreCase(ITEM)){
            entries.add(currentFeed);
            inItem = false;
        }
    }
    public void characters(char[] ch,int start, int length) throws SAXException{
        String lecture = new String(ch,start,length);
        if(buffer != null) buffer.append(lecture);
    }
    public ArrayList<Entry> getData(){
        return entries;
    }
}
