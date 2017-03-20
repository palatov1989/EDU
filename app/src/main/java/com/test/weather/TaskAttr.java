package com.test.weather;

import android.app.Activity;
import java.util.Date;

/**
 * Created by virus on 08.03.2017.
 */

public class TaskAttr {
    private ViewBinder binder;
    private Date date;
    private Activity content;
    public TaskAttr(Activity v, Date d, ViewBinder b){
        date=d; content=v; binder=b;
    }

    public ViewBinder getBinder() {
        return binder;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public Activity getContent() {
        return content;
    }
}
