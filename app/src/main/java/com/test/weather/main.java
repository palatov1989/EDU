package com.test.weather;

/**
 * Created by virus on 05.03.2017.
 */

public class Main {
    private   String  temp_min="";
    private   String  temp_max="";
    private    String humidity="";
    private    String asl_pressure="";
    private    String gnd_pressure="";

    public void Main(){}

    public String getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(String temp_min) {
        this.temp_min = temp_min;
    }

    public String getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(String temp_max) {
        this.temp_max = temp_max;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getAsl_pressure() {
        return asl_pressure;
    }

    public void setAsl_pressure(String asl_pressure) {
        this.asl_pressure = asl_pressure;
    }

    public String getGnd_pressure() {
        return gnd_pressure;
    }

    public void setGnd_pressure(String gnd_pressure) {
        this.gnd_pressure = gnd_pressure;
    }
}
