package com.test.weather;

import android.view.View;
import android.widget.ImageView;

/**
 * Created by virus on 13.03.2017.
 */

public class week_cast_element {
    private String date="";
    private String text="";
    private ImageView img;

    public week_cast_element(){}

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }
}
