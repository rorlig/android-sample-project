package com.example.android.rssfeed;


/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 8/4/13
 * Time: 2:01 AM
 * To change this template use File | Settings | File Templates.
 */
public class Item {
    public static final int NORMAL = 1;
    public static final int HEADER = 2;
    private String title;
    private int type;
    private String header;

    public Item(String title, String header, int type) {
        this.title = title;
        this.type = type;
        this.header = header;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int isType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    @Override
    public String toString() {
        return "Item [title=" + title + ", type=" + type + "]";
    }
}
