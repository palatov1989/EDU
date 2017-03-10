package com.test.weather;

import java.util.Date;

/**
 * Created by virus on 05.03.2017.
 */

public class List_element {
    private Weather weather;
    private Wind wind;
    private Main main;
    private String dt_txt;
    private Date date;


    public List_element() {
        weather = new Weather();
        wind = new Wind();
        main = new Main();
        date = new Date();
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public Wind getWind() {
        return wind;
    }

    public Main getMain() {
        return main;
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


