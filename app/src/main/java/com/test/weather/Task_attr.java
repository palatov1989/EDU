package com.test.weather;

import android.view.View;

import java.util.Date;

/**
 * Created by virus on 08.03.2017.
 */

public class Task_attr{
    private ViewBinder binder;
    private Date date;
    private View content;
    public  Task_attr(Date d, View v, ViewBinder b){
        date=d; content=v; binder=b;
    }

    public ViewBinder getBinder() {
        return binder;
    }
    public void setBinder(ViewBinder binder) {
        this.binder = binder;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public View getContent() {
        return content;
    }
    public void setContent(View content) {
        this.content = content;
    }
}
