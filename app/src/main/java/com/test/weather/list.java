package com.test.weather;

/**
 * Created by virus on 05.03.2017.
 */

public class List {
    private Weather weather;
    private Wind    wind;
    private Main    main;
    private String  data;

    public List(){
        weather = new Weather();
        wind    = new Wind();
        main    = new Main();
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

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
