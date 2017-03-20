package com.test.weather;

import java.util.Date;

/**
 * Created by virus on 05.03.2017.
 */

public class ListElement {
    private Weather weather;
    private WindWeather windWeather;
    private MainWeather mainWeather;
    private String dt_txt;
    private Date date;


    public ListElement() {
        weather = new Weather();
        windWeather = new WindWeather();
        mainWeather = new MainWeather();
        date = new Date();
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public WindWeather getWindWeather() {
        return windWeather;
    }

    public MainWeather getMainWeather() {
        return mainWeather;
    }

    public String getDt_txt() {
        return dt_txt;
    }

    public void setDt_txt(String dt_txt) {
        this.dt_txt = dt_txt;
    }

    public Date getDate() {
        return date;
    }

}


